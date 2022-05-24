package com.rawchen.mall.order.utils;

import com.rawchen.mall.order.entity.ExcelHeader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author zhaokz
 * @date 2021/3/22
 */
public class ExcelUtil {

	public static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

	static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * 导出多个sheet的excel
	 *
	 * @param name
	 * @param mapList
	 * @param response
	 * @param <T>
	 */
	public static <T> void exportMultisheetExcel(String name, List<Map> mapList, HttpServletResponse response) {
		BufferedOutputStream bos = null;
		try {
			String fileName = name + ".xlsx";
			bos = getBufferedOutputStream(fileName, response);
			doExport(mapList, bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导出多个sheet的excel,宽度自定义
	 *
	 */
	public static <T> void exportMultisheetExcelForWidthAdaptive(String name, List<Map> mapList, HttpServletResponse response) {
		BufferedOutputStream bos = null;
		try {
			String fileName = name + ".xlsx";
			bos = getBufferedOutputStream(fileName, response);
			doExportForWidthAdaptive(mapList, bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从excel中读内容
	 *
	 * @param filePath
	 * @param sheetIndex
	 * @return
	 */
	public static List<Map<String, String>> readExcel(String filePath, Integer sheetIndex) {
		List<Map<String, String>> dataList = new ArrayList<>();
		Workbook wb = ExcelUtil.createWorkBook(filePath);
		if (wb != null) {
			Sheet sheet = wb.getSheetAt(sheetIndex);
			int maxRownum = sheet.getPhysicalNumberOfRows();
			Row firstRow = sheet.getRow(0);
			int maxColnum = firstRow.getPhysicalNumberOfCells();
			String columns[] = new String[maxColnum];
			for (int i = 0; i < maxRownum; i++) {
				Map<String, String> map = null;
				if (i > 0) {
					map = new LinkedHashMap<>();
					firstRow = sheet.getRow(i);
				}
				if (firstRow != null) {
					String cellData = null;
					for (int j = 0; j < maxColnum; j++) {
						cellData = (String) ExcelUtil.getCellFormatValue(firstRow.getCell(j));
						if (i == 0) {
							columns[j] = cellData;
						} else {
							map.put(columns[j], cellData);
						}
					}
				} else {
					break;
				}
				if (i > 0) {
					dataList.add(map);
				}
			}
		}
		return dataList;
	}

	private static BufferedOutputStream getBufferedOutputStream(String fileName, HttpServletResponse response) throws Exception {
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
		return new BufferedOutputStream(response.getOutputStream());
	}

	private static <T> void doExport(List<Map> mapList, OutputStream outputStream) {
		int maxBuff = 100;
		// 创建excel工作文本，100表示默认允许保存在内存中的行数
		SXSSFWorkbook wb = new SXSSFWorkbook(maxBuff);
		try {
			for (int i = 0; i < mapList.size(); i++) {
				Map map = mapList.get(i);
				String[] headers = (String[]) map.get("headers");
				Collection<T> dataList = (Collection<T>) map.get("dataList");
				String fileName = (String) map.get("fileName");
				String[] exportFields = (String[]) map.get("exportFields");
				createSheet(wb, exportFields, headers, dataList, fileName, maxBuff);
			}

			if (outputStream != null) {
				wb.write(outputStream);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 导出宽度自适应的sheet
	 *
	 */
	private static <T> void doExportForWidthAdaptive(List<Map> mapList, OutputStream outputStream) {
		int maxBuff = 100;
		// 创建excel工作文本，100表示默认允许保存在内存中的行数
		SXSSFWorkbook wb = new SXSSFWorkbook(maxBuff);
		try {
			for (int i = 0; i < mapList.size(); i++) {
				Map map = mapList.get(i);
				String[] headers = (String[]) map.get("headers");
				Collection<T> dataList = (Collection<T>) map.get("dataList");
				String fileName = (String) map.get("fileName");
				String[] exportFields = (String[]) map.get("exportFields");
				createSheetForWidthAdaptive(wb, exportFields, headers, dataList, fileName, maxBuff);
			}

			if (outputStream != null) {
				wb.write(outputStream);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 创建sheet,宽度自定义,最大宽度为7680
	 */
	private static <T> void createSheetForWidthAdaptive(SXSSFWorkbook wb, String[] exportFields, String[] headers, Collection<T> dataList, String fileName, int maxBuff) throws NoSuchFieldException, IllegalAccessException, IOException {

		SXSSFSheet sh = wb.createSheet(fileName);

		CellStyle style = wb.createCellStyle();
		CellStyle style2 = wb.createCellStyle();
		//创建表头
		Font font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 11);//设置字体大小
		style.setFont(font);//选择需要用到的字体格式

		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getColor().getIndex());// 设置背景色
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER); // 居中
		style.setBorderBottom(BorderStyle.THIN); //下边框
		style.setBorderRight(BorderStyle.THIN);//右边框

		style2.setFont(font);//选择需要用到的字体格式

		style2.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getColor().getIndex());// 设置背景色
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style2.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
		style2.setAlignment(HorizontalAlignment.CENTER); // 水平向下居中
		style2.setBorderBottom(BorderStyle.THIN); //下边框
		style2.setBorderRight(BorderStyle.THIN);//右边框
		style2.setBorderLeft(BorderStyle.THIN);//左边框
		style2.setBorderTop(BorderStyle.THIN);//上边框
		style2.setWrapText(true);
		Row headerRow = sh.createRow(0); //表头

        //最大宽度，为了实现宽度自适应
		Map<Integer, Integer> maxWidth = new HashMap<>();

		int headerSize = headers.length;
		for (int cellnum = 0; cellnum < headerSize; cellnum++) {
			Cell cell = headerRow.createCell(cellnum);
			cell.setCellStyle(style);
//			sh.setColumnWidth(cellnum, 4000);
			cell.setCellValue(headers[cellnum]);
			maxWidth.put(cellnum, cell.getStringCellValue().getBytes().length  * 256 + 200);
		}

		int rownum = 0;
		Iterator<T> iterator = dataList.iterator();
		while (iterator.hasNext()) {
			T data = iterator.next();
			Row row = sh.createRow(rownum + 1);

			Field[] fields = getExportFields(data.getClass(), exportFields);
			for (int cellnum = 0; cellnum < headerSize; cellnum++) {
				Cell cell = row.createCell(cellnum);
				cell.setCellStyle(style2);
				Field field = fields[cellnum];

				setData(field, data, field.getName(), cell);
				cell.setCellType(CellType.STRING);
				int length = cell.getStringCellValue().getBytes().length  * 256 + 200;
				//设置文本最大宽度，7880 = 200 + 256*30
				if(length >= 7880){
					length = 7880;
				}
				maxWidth.put(cellnum, Math.max(length, maxWidth.get(cellnum)));
			}
			rownum = sh.getLastRowNum();
			// 大数据量时将之前的数据保存到硬盘
			if (rownum % maxBuff == 0) {
				((SXSSFSheet) sh).flushRows(maxBuff); // 超过100行后将之前的数据刷新到硬盘

			}
		}
		//设置excel导出列宽度自适应
		for (int i = 0; i < headerSize; i++) {
			sh.setColumnWidth(i, maxWidth.get(i));
		}

	}

	/**
	 * 创建sheet,宽度限定为4000
	 */
	private static <T> void createSheet(SXSSFWorkbook wb, String[] exportFields, String[] headers, Collection<T> dataList, String fileName, int maxBuff) throws NoSuchFieldException, IllegalAccessException, IOException {

		Sheet sh = wb.createSheet(fileName);

		CellStyle style = wb.createCellStyle();
		CellStyle style2 = wb.createCellStyle();
		//创建表头
		Font font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 11);//设置字体大小
		style.setFont(font);//选择需要用到的字体格式

		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getColor().getIndex());// 设置背景色
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER); // 居中
		style.setBorderBottom(BorderStyle.THIN); //下边框
		style.setBorderRight(BorderStyle.THIN);//右边框

		style2.setFont(font);//选择需要用到的字体格式

		style2.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getColor().getIndex());// 设置背景色
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style2.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
		style2.setAlignment(HorizontalAlignment.CENTER); // 水平向下居中
		style2.setBorderBottom(BorderStyle.THIN); //下边框
		style2.setBorderRight(BorderStyle.THIN);//右边框
		style2.setBorderLeft(BorderStyle.THIN);//左边框
		style2.setBorderTop(BorderStyle.THIN);//上边框

		Row headerRow = sh.createRow(0); //表头

		int headerSize = headers.length;
		for (int cellnum = 0; cellnum < headerSize; cellnum++) {
			Cell cell = headerRow.createCell(cellnum);
			cell.setCellStyle(style);
			sh.setColumnWidth(cellnum, 4000);
			cell.setCellValue(headers[cellnum]);
		}

		int rownum = 0;
		Iterator<T> iterator = dataList.iterator();
		while (iterator.hasNext()) {
			T data = iterator.next();
			Row row = sh.createRow(rownum + 1);

			Field[] fields = getExportFields(data.getClass(), exportFields);
			for (int cellnum = 0; cellnum < headerSize; cellnum++) {
				Cell cell = row.createCell(cellnum);
				cell.setCellStyle(style2);
				Field field = fields[cellnum];

				setData(field, data, field.getName(), cell);
			}
			rownum = sh.getLastRowNum();
			// 大数据量时将之前的数据保存到硬盘
			if (rownum % maxBuff == 0) {
				((SXSSFSheet) sh).flushRows(maxBuff); // 超过100行后将之前的数据刷新到硬盘

			}
		}
	}

	private static <T> void doExport(String[] headers, String[] exportFields, Collection<T> dataList,
									 String fileName, OutputStream outputStream) {

		int maxBuff = 100;
		// 创建excel工作文本，100表示默认允许保存在内存中的行数
		SXSSFWorkbook wb = new SXSSFWorkbook(maxBuff);
		try {
			createSheet(wb, exportFields, headers, dataList, fileName, maxBuff);
			if (outputStream != null) {
				wb.write(outputStream);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 获取单条数据的属性
	 *
	 * @param object
	 * @param property
	 * @param <T>
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private static <T> Field getDataField(T object, String property) throws NoSuchFieldException, IllegalAccessException {
		Field dataField;
		if (property.contains(".")) {
			String p = property.substring(0, property.indexOf("."));
			dataField = object.getClass().getDeclaredField(p);
			return dataField;
		} else {
			dataField = object.getClass().getDeclaredField(property);
		}
		return dataField;
	}

	private static Field[] getExportFields(Class<?> targetClass, String[] exportFieldNames) {
		Field[] fields = null;
		if (exportFieldNames == null || exportFieldNames.length < 1) {
			fields = targetClass.getDeclaredFields();
		} else {
			fields = new Field[exportFieldNames.length];
			for (int i = 0; i < exportFieldNames.length; i++) {
				try {
					fields[i] = targetClass.getDeclaredField(exportFieldNames[i]);
				} catch (Exception e) {
					try {
						fields[i] = targetClass.getSuperclass().getDeclaredField(exportFieldNames[i]);
					} catch (Exception e1) {
						throw new IllegalArgumentException("无法获取导出字段", e);
					}

				}
			}
		}
		return fields;
	}

	/**
	 * 根据属性设置对应的属性值
	 *
	 * @param dataField 属性
	 * @param object    数据对象
	 * @param property  表头的属性映射
	 * @param cell      单元格
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	private static <T> void setData(Field dataField, T object, String property, Cell cell)
			throws IllegalAccessException, NoSuchFieldException {
		dataField.setAccessible(true); //允许访问private属性
		Object val = dataField.get(object); //获取属性值
		Sheet sh = cell.getSheet(); //获取excel工作区
		CellStyle style = cell.getCellStyle(); //获取单元格样式
		int cellnum = cell.getColumnIndex();
		if (val != null) {
			if (dataField.getType().toString().endsWith("String")) {
				cell.setCellValue((String) val);
			} else if (dataField.getType().toString().endsWith("Integer") || dataField.getType().toString().endsWith("int")) {
				cell.setCellValue((Integer) val);
			} else if (dataField.getType().toString().endsWith("Long") || dataField.getType().toString().endsWith("long")) {
				cell.setCellValue(val.toString());
			} else if (dataField.getType().toString().endsWith("Double") || dataField.getType().toString().endsWith("double")) {
				cell.setCellValue((Double) val);
			} else if (dataField.getType().toString().endsWith("Date")) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cell.setCellValue(format.format((Date) val));
			} else if (dataField.getType().toString().endsWith("List")) {
				List list1 = (List) val;
				int size = list1.size();
				for (int i = 0; i < size; i++) {
					//加1是因为要去掉点号
					int start = property.indexOf(dataField.getName()) + dataField.getName().length() + 1;
					String tempProperty = property.substring(start, property.length());
					Field field = getDataField(list1.get(i), tempProperty);
					Cell tempCell = cell;
					if (i > 0) {
						int rowNum = cell.getRowIndex() + i;
						Row row = sh.getRow(rowNum);
						if (row == null) {//另起一行
							row = sh.createRow(rowNum);
							//合并之前的空白单元格（在这里需要在header中按照顺序把list类型的字段放到最后，方便显示和合并单元格）
							for (int j = 0; j < cell.getColumnIndex(); j++) {
								sh.addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex() + size - 1, j, j));
								Cell c = row.createCell(j);
								c.setCellStyle(style);
							}
						}
						tempCell = row.createCell(cellnum);
						tempCell.setCellStyle(style);
					}
					//递归传参到单元格并获取偏移量（这里获取到的偏移量都是第二层后list的偏移量）
					setData(field, list1.get(i), tempProperty, tempCell);
				}
			} else {
				if (property.contains(".")) {
					String p = property.substring(property.indexOf(".") + 1, property.length());
					Field field = getDataField(val, p);
					setData(field, val, p, cell);
				} else {
					cell.setCellValue(val.toString());
				}
			}
		}
	}


	private static Workbook createWorkBook(String filePath) {
		Workbook wb = null;
		if (filePath == null) {
			return null;
		}
		String extString = filePath.substring(filePath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * 将字段转为相应的格式
	 *
	 * @param cell
	 * @return
	 */
	private static Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			//判断cell类型
			switch (cell.getCellType()) {
				case NUMERIC: {
					cellValue = String.valueOf(cell.getNumericCellValue());
					break;
				}
				case FORMULA: {
					if (DateUtil.isCellDateFormatted(cell)) {
						//转换为日期格式YYYY-mm-dd
						cellValue = cell.getDateCellValue();
					} else {
						//数字
						cellValue = String.valueOf(cell.getNumericCellValue());
					}
					break;
				}
				case STRING: {
					cellValue = cell.getRichStringCellValue().getString();
					break;
				}
				default:
					cellValue = "";
			}
		} else {
			cellValue = "";
		}
		return cellValue;
	}

	/**
	 * @param multipartFile
	 * @param clz           VO对象，对应Excel表头
	 * @param sheetIndex    指定sheet索引
	 * @param <T>
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static <T> List<T> importExcel(MultipartFile multipartFile, Class<T> clz, int sheetIndex) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		if (null == multipartFile) {
			throw new NullPointerException("请选择文件");
		}

		log.info(multipartFile.getName());

		log.info("文件类型:{}", multipartFile.getContentType());
		String fileName = multipartFile.getOriginalFilename();
		log.info("文件名:{}", fileName);

		if (!"application/vnd.ms-excel".equals(multipartFile.getContentType()) && !"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(multipartFile.getContentType())) {
			throw new RuntimeException("请选择正确的文件类型与文件!");
		}

		// 返回数据
		List<T> list = new ArrayList<>();

		InputStream inputStream = multipartFile.getInputStream();
		Workbook wb = WorkbookFactory.create(inputStream);

		// 通过指定sheet索引读取Sheet
		Sheet sheet = wb.getSheetAt(sheetIndex);
		// 获取最大行数(或者sheet.getLastRowNum())
		int rownum = sheet.getPhysicalNumberOfRows();
		// 反射获取字段
		Field[] fields = clz.getDeclaredFields();
		// 获取第一行(表头)
		Row row = sheet.getRow(0);
		// 获取最大列数
		int column = row.getPhysicalNumberOfCells();

		// 表头校验
//		for (int j = 0; j < fields.length; j++) {
//			Field field = fields[j];
//			if (field.isAnnotationPresent(ExcelHeader.class)) {
//				ExcelHeader annotation = field.getAnnotation(ExcelHeader.class);
//				Cell cell = row.getCell(j);
//				if (cell == null || !getCellValue(cell).equals(annotation.value())) {
//					throw new RuntimeException("Excel格式错误");
//				}
//			}
//		}

		// 处理行数据
		for (int i = 1; i < rownum; i++) {

			row = sheet.getRow(i);
			// 遇到空行则结束
			if (row == null) {
				break;
			}

			T rowData = clz.getDeclaredConstructor().newInstance();

			// 处理列数据
			for (int j = 0; j < fields.length; j++) {

				Field field = fields[j];
				// 设置属性可访问
				field.setAccessible(true);

				if (field.isAnnotationPresent(ExcelHeader.class)) {

					ExcelHeader annotation = field.getAnnotation(ExcelHeader.class);

					// 这里默认按列顺序，也可以根据columnIndex设置列顺序
					int columnIndex = annotation.columnIndex();

					Cell cell = row.getCell(j);
					if (cell == null) {
						continue;
					}
					// 获取列值
					Object value = getCellValue(cell);
					// 设置属性
					setFieldValue(rowData, field, value);
				}

			}
			list.add(rowData);
		}
		log.info("上传数据={}", list.toString());
		return list;
	}

	/**
	 * @param multipartFile
	 * @param clz           VO对象，对应Excel表头
	 * @param <T>
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static <T> List<T> importExcel(MultipartFile multipartFile, Class<T> clz) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		return importExcel(multipartFile, clz, 0);
	}

	private static <T> void setFieldValue(T rowData, Field field, Object value) throws IllegalAccessException {

		if (field.getType() == int.class || field.getType() == Integer.class) {
			field.set(rowData, value);
		} else if (field.getType() == long.class || field.getType() == Long.class) {
			field.set(rowData, value);
		} else if (field.getType() == double.class || field.getType() == Double.class) {
			field.set(rowData, value);
		} else if (field.getType() == String.class) {
			if(value == null){
				field.set(rowData, null);
			} else {
				field.set(rowData, String.valueOf(value));
			}
		} else if (field.getType() == LocalDateTime.class) {
			field.set(rowData, LocalDateTime.parse(String.valueOf(value), dateTimeFormatter));
		}
	}

	private static Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellType();
		Object cellValue = null;

		if (cellType == CellType._NONE) {

		} else if (cellType == CellType.NUMERIC) {
			// 数值型
			if (DateUtil.isCellDateFormatted(cell)) {
				// 日期类型
				Date d = cell.getDateCellValue();
				cellValue = dateTimeFormatter.format(LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()));
			} else {
				double numericCellValue = cell.getNumericCellValue();
				BigDecimal bdVal = new BigDecimal(numericCellValue);
				if ((bdVal + ".0").equals(Double.toString(numericCellValue))) {
					// 整型
					cellValue = bdVal;
				} else if (String.valueOf(numericCellValue).contains("E10")) {
					// 科学记数法
					cellValue = new BigDecimal(numericCellValue).toPlainString();
				} else {
					// 浮点型
					cellValue = numericCellValue;
				}
			}
		} else if (cellType == CellType.STRING) {
			// 字符串型
			cellValue = cell.getStringCellValue().replaceAll(" ", "");
		} else if (cellType == CellType.FORMULA) {
			// 公式型
		} else if (cellType == CellType.BLANK) {
			// 空值
		} else if (cellType == CellType.BOOLEAN) {
			// 布尔型
			cellValue = cell.getBooleanCellValue();
		} else if (cellType == CellType.ERROR) {
			// 错误
			cellValue = cell.getErrorCellValue();
		}

		log.info("cellType={}, cellValue={}", cellType.name(), cellValue);
		return cellValue;
	}

}

