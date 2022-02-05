package com.rawchen.common.utils;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * 平台代码生成工具 - 【微服务】单模块多表生成
 *
 * @author RawChen
 * @version v1.0
 * @since 2021-10-30
 */
public class GeneratorUtil {

	// 【必须】作者名称
	protected String author = "RawChen";

	// 【必须】要生成代码的数据库表名, 如为空则生成该库内全部表对应的代码文件
	protected String customTableNames[] = {};

	// 【必须】源文件夹存放路径，也是包路径
	protected String package_url = "com/rawchen/mall/product";

	// 【必须】模块名
	protected String moduleName = "mall-product";

	// 【必须】数据库连接参数
	protected String url = "jdbc:mysql://localhost:3306/mall_pms" + "?characterEncoding=utf8";
	protected String driverName = "com.mysql.cj.jdbc.Driver";
	protected String user = "root";
	protected String password = "root";

	// 【可选】生成的带代码文件名称忽略表名前缀, 不忽略则为空
	protected String ignoreTablePrefix = "pms_";

	// 【可选】版本接口路由，例如：/api/v1
	protected String versionInterfaceRouting = "";

	// 【保持】包路径
	private final String bean_package = package_url.replaceAll("/", ".") + ".entity";
	private final String service_package = package_url.replaceAll("/", ".") + ".service";
	private final String mapper_package = package_url.replaceAll("/", ".") + ".mapper";
	private final String controller_package = package_url.replaceAll("/", ".") + ".controller";

	// 【保持】生成的文件存放目录
	protected String project_path = System.getProperty("user.dir") + "\\";

	// 【保持】其他路径配置
	protected String root_path_full = project_path + moduleName + "/src/main/java/" + package_url;
	protected String bean_path = project_path + moduleName + "/src/main/java/" + package_url + "/entity";
	protected String mapper_path = project_path + moduleName + "/src/main/java/" + package_url + "/mapper";
	protected String service_path = project_path + moduleName + "/src/main/java/" + package_url + "/service";
	protected String controller_path = project_path + moduleName + "/src/main/java/" + package_url + "/controller";
	protected String xml_path = project_path + moduleName + "/src/main/resources/mapper";

	// 【保持】MySQL数据库的数据类型，代码中判断使用
	private final String type_char = "char";
	private final String type_date = "date";
	private final String type_timestamp = "timestamp";
	private final String type_int = "int";
	private final String type_bigint = "bigint";
	private final String type_text = "text";
	private final String type_bit = "bit";
	private final String type_decimal = "decimal";
	private final String type_blob = "blob";


	public static void main(String[] args) {
        try {
            GeneratorUtil generator = new GeneratorUtil();
            generator.generate();
            // 自动打开生成文件的目录
            Runtime.getRuntime().exec("cmd /c start explorer " + generator.root_path_full.replace("/", "\\"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
	}

	/**
	 * 生成代码主入口方法
	 */
	public void generate() throws ClassNotFoundException, SQLException, IOException {
		init();
		String prefix = "show full fields from ";
		List<String> columns = null;
		List<String> types = null;
		List<String> comments = null;
		PreparedStatement pstate = null;
		List<String> tables = getTables();
		Map<String, String> tableComments = getTableComment();
		for (String table : tables) {
			if ("schema_version".equalsIgnoreCase(table))
				continue;
			columns = new ArrayList<String>();
			types = new ArrayList<String>();
			comments = new ArrayList<String>();
			tableName = table;
			tableNameFull = ignoreTablePrefix + table;
			pstate = conn.prepareStatement(prefix + tableNameFull);
			ResultSet results = pstate.executeQuery();
			while (results.next()) {
				columns.add(results.getString("FIELD").toLowerCase());
				types.add(results.getString("TYPE").toLowerCase());
				comments.add(results.getString("COMMENT").toLowerCase());
			}
			processTable(tableName);
			String tableComment = tableComments.get(tableNameFull);
			createRootDir(root_path_full);

			buildEntityBean(columns, types, comments, tableComment, beanName, bean_package, bean_path);
			buildMapper(tableComment, beanName);
			buildMapperXml(columns, types, comments, tableComment);
			buildService(service_path, serviceName, service_package, tableComment);
			buildController(controller_path, controllerName, controller_package, columns, types, comments, tableComment);
		}
		conn.close();
	}

	private String tableName = null;
	private String tableNameFull = null;
	private String beanName = null;
	private String mapperName = null;
	private String serviceName = null;
	private String controllerName = null;

	private Connection conn = null;

	private void init() throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
	}

	/**
	 * 获取所有的表名
	 *
	 * @return
	 * @throws SQLException
	 */
	private List<String> getTables() throws SQLException {
		List<String> tables = new ArrayList<>();
		PreparedStatement pstate = conn.prepareStatement("show tables");
		ResultSet results = pstate.executeQuery();
		while (results.next()) {
			// 是否输出自定义的表
			if (customTableNames.length > 0) {
				for (String customTableName : customTableNames) {
					String tableName = results.getString(1);
					if (customTableName.toUpperCase().equalsIgnoreCase(tableName)) {
						if (tableName.toLowerCase().startsWith(ignoreTablePrefix.toLowerCase())) {
							customTableName = customTableName.substring(ignoreTablePrefix.length());
						}
						tables.add(customTableName);
					}
				}
			} else {
				String tableName = results.getString(1);
				if (tableName.toLowerCase().startsWith(ignoreTablePrefix.toLowerCase())) {
					tableName = tableName.substring(ignoreTablePrefix.length());
				}
				tables.add(tableName);
			}
		}
		return tables;
	}

	/**
	 * 根据表明拼接各个文件名称
	 *
	 * @param table
	 */
	private void processTable(String table) {
		StringBuffer sb = new StringBuffer(table.length());
		String tableNew = table.toLowerCase();
		String[] tables = tableNew.split("_");
		String temp = null;
		for (int i = 0; i < tables.length; i++) {
			temp = tables[i].trim();
			sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
		}
		beanName = sb.toString() + "";
		mapperName = sb.toString() + "Mapper";
		serviceName = sb.toString() + "Service";
		controllerName = sb.toString() + "Controller";
	}

	private String processType(String type) {
		if (type.contains(type_char)) {
			return "String";
		} else if (type.contains(type_bigint)) {
			return "Long";
		} else if (type.contains(type_int)) {
			return "Integer";
		} else if (type.contains(type_date)) {
			return "Date";
		} else if (type.contains(type_text)) {
			return "String";
		} else if (type.contains(type_timestamp)) {
			return "Date";
		} else if (type.contains(type_bit)) {
			return "Boolean";
		} else if (type.contains(type_decimal)) {
			return "java.lang.Float";
		} else if (type.contains(type_blob)) {
			return "byte[]";
		}
		return null;
	}


	private String processTypeDTO(String type) {
		if (type.contains(type_char)) {
			return "String";
		} else if (type.contains(type_bigint)) {
			return "Long";
		} else if (type.contains(type_int)) {
			return "Integer";
		} else if (type.contains(type_date)) {
			return "Long";
		} else if (type.contains(type_text)) {
			return "String";
		} else if (type.contains(type_timestamp)) {
			return "Long";
		} else if (type.contains(type_bit)) {
			return "Boolean";
		} else if (type.contains(type_decimal)) {
			return "java.lang.Float";
		} else if (type.contains(type_blob)) {
			return "byte[]";
		}
		return null;
	}

	private String processField(String field) {
		StringBuffer sb = new StringBuffer(field.length());
		String[] fields = field.split("_");
		String temp = null;
		sb.append(fields[0]);
		for (int i = 1; i < fields.length; i++) {
			temp = fields[i].trim();
			sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
		}
		return sb.toString();
	}

	/**
	 * 将实体类名首字母改为小写
	 *
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unused")
	private String processResultMapId(String beanName) {
		return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
	}

	private String processResultMapIdUpper(String beanName) {
		if (beanName.contains("_")) {
			String name = "";
			String strs[] = beanName.split("_");
			for (String str : strs) {
				name += str.substring(0, 1).toUpperCase() + str.substring(1);
			}
			return name;
		}
		return beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
	}

	/**
	 * 构建类上面的注释
	 *
	 * @param bw
	 * @param text
	 * @return
	 * @throws IOException
	 */
	private BufferedWriter buildClassComment(BufferedWriter bw, String text) throws IOException {
		write(bw, "/**");
		write(bw, " * " + text);
		write(bw, " * Created" + (stringNotNull(author) ? " by " + author + "" : "") + " on " + getDate() + ".");
		write(bw, " **/");
		return bw;
	}

	/**
	 * 构建方法上面的注释
	 *
	 * @param bw
	 * @param text
	 * @return
	 * @throws IOException
	 */
	private BufferedWriter buildMethodComment(BufferedWriter bw, String text) throws IOException {
		write(bw, "\t/**");
		write(bw, "\t * ");
		write(bw, "\t * " + text);
		write(bw, "\t * ");
		write(bw, "\t **/");
		return bw;
	}

	/**
	 * 创建根目录
	 */
	private void createRootDir(String rootPath) {
		File folder = new File(rootPath);
		if (!folder.exists()) {
			folder.mkdir();
		}
	}


	/**
	 * 生成实体类
	 *
	 * @param columns
	 * @param types
	 * @param comments
	 * @throws IOException
	 */
	private void buildEntityBean(List<String> columns, List<String> types, List<String> comments, String tableComment,
								 String className, String packName, String path) throws IOException {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}

		File beanFile = new File(path, className + ".java");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
		write(bw, "package " + packName + ";");
		bw.newLine();
//		write(bw, "import " + package_url.replaceAll("/", ".") + ".base.DataEntity;");
		write(bw, "import com.rawchen.common.entity.DataEntity;");
		write(bw, "import io.swagger.annotations.ApiModel;");
		write(bw, "import io.swagger.annotations.ApiModelProperty;");
		write(bw, "import java.io.Serializable;");
		write(bw, "import java.util.Date;");
		bw.newLine();
		buildClassComment(bw, (stringNotNull(tableComment) ? tableComment + " " : "") + "[" + className + "]数据对象.");
		write(bw, "@ApiModel(value = \"" + className + "\", description = \"" + (stringNotNull(tableComment) ? tableComment : className) + "对象\")");
		write(bw, "public class " + className + " extends DataEntity<" + className + "> implements Serializable {");
		bw.newLine();
		int size = columns.size();
		for (int i = 0; i < size; i++) {
			if (!columns.get(i).equalsIgnoreCase("UPDATE_TIME")
					&& !columns.get(i).equalsIgnoreCase("CREATE_TIME")) {
				if (stringNotNull(comments.get(i))) {
					write(bw, "\t/**");
					write(bw, "\t * " + comments.get(i));
					write(bw, "\t */");
					write(bw, "\t@ApiModelProperty(value = \"" + comments.get(i) + "\")");
				} else {
					write(bw, "\t@ApiModelProperty(value = \"" + columns.get(i) + "\")");
				}

				write(bw, "\tprivate " + processType(types.get(i)) + " " + processField(columns.get(i)) + ";");
				bw.newLine();
			}
		}
		// 生成get/set方法
		String tempField = null;
		String _tempField = null;
		String tempType = null;
		for (int i = 0; i < size; i++) {
			if (!columns.get(i).equalsIgnoreCase("UPDATE_TIME")
					&& !columns.get(i).equalsIgnoreCase("CREATE_TIME")) {
				tempType = processType(types.get(i));
				_tempField = processField(columns.get(i));
				tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
				write(bw, "\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
				write(bw, "\t\tthis." + _tempField + " = " + _tempField + ";");
				write(bw, "\t}");
				write(bw, "\tpublic " + tempType + " get" + tempField + "(){");
				write(bw, "\t\treturn " + _tempField + ";");
				write(bw, "\t}");
			}
		}
		bw.write("}");
		bw.flush();
		bw.close();
	}


	/**
	 * 生成Mapper文件
	 *
	 * @throws IOException
	 */
	private void buildMapper(String tableComment, String className) throws IOException {
		File folder = new File(mapper_path);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		File mapperFile = new File(mapper_path, mapperName + ".java");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
		write(bw, "package " + mapper_package + ";");
		bw.newLine();
		write(bw, "import " + bean_package + "." + beanName + ";");
//		write(bw, "import " + package_url.replaceAll("/", ".") + ".base.BaseMapper;");
		write(bw, "import com.rawchen.common.base.BaseMapper;");
		write(bw, "import org.apache.ibatis.annotations.Mapper;");
		bw.newLine();
		buildClassComment(bw, (stringNotNull(tableComment) ? tableComment + " " : "") + "[" + className + "]数据层数据库操作接口类.");
		write(bw, "@Mapper");
		write(bw, "public interface " + mapperName + " extends BaseMapper<" + beanName + "> {");
		write(bw, "\t");
		bw.write("}");
		bw.flush();
		bw.close();
	}

	/**
	 * 构建实体类映射XML文件
	 *
	 * @param columns
	 * @param types
	 * @param comments
	 * @throws IOException
	 */
	private void buildMapperXml(List<String> columns, List<String> types, List<String> comments, String tableComment)
			throws IOException {
		File folder = new File(xml_path);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		File mapperXmlFile = new File(xml_path, mapperName + ".xml");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
		write(bw, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		write(bw, "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
		write(bw, "    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
		bw.newLine();
		write(bw, "<!-- " + tableNameFull + " " + tableComment + " -->");
		write(bw, "<mapper namespace=\"" + mapper_package + "." + mapperName + "\">");
		bw.newLine();
		buildSQL(bw, columns, types);
		bw.newLine();
		write(bw, "</mapper>");
		bw.flush();
		bw.close();
	}

	/**
	 * 添加映射XML文件的resultMap和CRUD
	 * @param bw
	 * @param columns
	 * @param types
	 * @throws IOException
	 */
	private void buildSQL(BufferedWriter bw, List<String> columns, List<String> types) throws IOException {
		String resultMapName = "entityResultMap";
		String beanNameFull = bean_package + "." + beanName;
		int size = columns.size();
		write(bw, "\t<resultMap id=\"" + resultMapName + "\" type=\"" + beanNameFull + "\">");
		for (String column : columns) {
			String tempField = processField(column);
			if ("id".equalsIgnoreCase(column)) {
				write(bw, "\t\t<id property=\"" + tempField + "\" column=\"" + column + "\" />");
			} else {
				write(bw, "\t\t<result property=\"" + tempField + "\" column=\"" + column + "\"/>");
			}
		}
		write(bw, "\t</resultMap>");
		bw.newLine();
		write(bw, "\t<sql id=\"allColumnFiled\">");
		for (int i = 0; i < size; i++) {
			bw.write("\t\t`" + columns.get(i) + "`");
			if (i != size - 1) {
				bw.write(",");
			}
			bw.newLine();
		}
		write(bw, "\t</sql>");
		bw.newLine();

		write(bw, "\t<!-- 查询 -->");
		write(bw, "\t<select id=\"get\" resultMap=\"" + resultMapName + "\">");
		write(bw, "\t\tSELECT ");
		write(bw, "\t\t<include refid=\"allColumnFiled\"/>");
		write(bw, "\t\tFROM " + tableNameFull);
		write(bw, "\t\tWHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
		write(bw, "\t</select>");
		bw.newLine();
		write(bw, "\t<!-- 查询列表 -->");
		write(bw, "\t<select id=\"findList\" resultMap=\"" + resultMapName + "\" parameterType=\"" + beanNameFull + "\">");
		write(bw, "\t\tSELECT");
		write(bw, "\t\t<include refid=\"allColumnFiled\"/>");
		write(bw, "\t\tFROM " + tableNameFull);
		if (columns.size() >= 1) {
			write(bw, "\t\t<where>");
		}
		for (int i = 0; i < columns.size(); i++) {
			String column = columns.get(i);
			String tempField = processField(column);
			String type = processType(types.get(i));
			write(bw, "\t\t\t<if test=\"" + tempField + "!=null " + (type.equals("String") ? "and " + tempField + "!=''" : "") + "\">");
			write(bw, "\t\t\t\tAND " + column + " like concat('%',concat(#{" + tempField + "},'%'))");
			write(bw, "\t\t\t</if>");
		}
		if (columns.size() >= 1) {
			write(bw, "\t\t</where>");
		}
		write(bw, "\t</select>");
		bw.newLine();

		write(bw, "\t<!-- 保存 -->");
		write(bw, "\t<insert id=\"insert\" parameterType=\"" + beanNameFull + "\">");
		write(bw, "\t\tINSERT INTO " + tableNameFull);
		write(bw, " \t\t\t(<include refid=\"allColumnFiled\"/>)");
		write(bw, "\t\tVALUES ");
		bw.write(" \t\t\t(");
		for (int i = 0; i < size; i++) {
			bw.write("#{" + processField(columns.get(i)) + "}");
			if (i != size - 1) {
				bw.write(", ");
			}
		}
		bw.write(")");
		bw.newLine();
		write(bw, "\t</insert>");
		bw.newLine();

		write(bw, "\t<!-- 更新 -->");
		write(bw, "\t<update id=\"update\" parameterType=\"" + beanNameFull + "\">");
		write(bw, "\t\tUPDATE " + tableNameFull);
		write(bw, "\t\tSET ");
		String tempField = null;
		for (int i = 1; i < size; i++) {
			tempField = processField(columns.get(i));
			String type = processType(types.get(i));

			write(bw, "\t\t\t<if test=\"" + tempField + "!=null " + (type.equals("String") ? "and " + tempField + "!=''" : "") + "\">");
			write(bw, "\t\t\t\t" + columns.get(i) + " = #{" + tempField + "}");
			if (i != size - 1) {
				bw.write(",");
			}
			write(bw, "\t\t\t</if>");
		}
		write(bw, "\t\tWHERE ");
		write(bw, "\t\t\t" + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
		write(bw, "\t</update>");
		bw.newLine();
		write(bw, "\t<!-- 删除 -->");
		write(bw, "\t<delete id=\"delete\">");
		write(bw, "\t\tdelete from " + tableNameFull + " where " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
		write(bw, "\t</delete>");

	}

	/**
	 * 获取所有的数据库表注释
	 *
	 * @return
	 * @throws SQLException
	 */
	private Map<String, String> getTableComment() throws SQLException {
		Map<String, String> maps = new HashMap<String, String>();
		PreparedStatement pstate = conn.prepareStatement("show table status");
		ResultSet results = pstate.executeQuery();
		while (results.next()) {
			String tableName = results.getString("NAME");
			String comment = results.getString("COMMENT");
			maps.put(tableName, comment);
		}
		return maps;
	}

	/**
	 * 生成Service
	 */
	private void buildService(String path, String className, String packName, String tableComment) throws IOException {

		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}

		File beanFile = new File(path, className + ".java");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
		String minMapperName = mapperName;
		minMapperName = minMapperName.substring(0, 1).toLowerCase() + minMapperName.substring(1);

		write(bw, "package " + packName + ";");
		bw.newLine();
		write(bw, "import org.springframework.stereotype.Service;");
		write(bw, "import org.springframework.transaction.annotation.Transactional;");
//		write(bw, "import " + package_url.replaceAll("/", ".") + ".base.BaseService;");
		write(bw, "import com.rawchen.common.base.BaseService;");
		write(bw, "import " + mapper_package + "." + mapperName + ";");
		write(bw, "import " + bean_package + "." + beanName + ";");
		bw.newLine();
		buildClassComment(bw, (stringNotNull(tableComment) ? tableComment + " " : "") + "[" + className + "]服务实现.");
		write(bw, "@Service");
		write(bw, "@Transactional(readOnly = false)");
		write(bw, "public class " + className + " extends BaseService<" + mapperName + ", " + beanName + "> {");
		write(bw, "\t");
		bw.write("}");
		bw.flush();
		bw.close();
	}

	/**
	 * 生成Controller
	 */
	private void buildController(String path, String className, String packName, List<String> columns, List<String> types, List<String> comments, String tableComment) throws IOException {
		String modelNote = stringNotNull(tableComment) ? tableComment.replace("表", "") : "";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}

		File beanFile = new File(path, className + ".java");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));

		write(bw, "package " + packName + ";");
		bw.newLine();
		write(bw, "import " + bean_package + "." + beanName + ";");
		write(bw, "import " + service_package + "." + serviceName + ";");
//		write(bw, "import " + package_url.replaceAll("/", ".") + ".base.BaseController;");
//		write(bw, "import " + package_url.replaceAll("/", ".") + ".base.Result;");
//		write(bw, "import " + package_url.replaceAll("/", ".") + ".base.Page;");
		write(bw, "import com.rawchen.common.base.BaseController;");
		write(bw, "import com.rawchen.common.entity.Result;");
		write(bw, "import com.rawchen.common.entity.Page;");
		write(bw, "import io.swagger.annotations.*;");
		write(bw, "import org.slf4j.Logger;");
		write(bw, "import org.slf4j.LoggerFactory;");
		write(bw, "import org.springframework.beans.factory.annotation.Autowired;");
		write(bw, "import org.springframework.web.bind.annotation.*;");
		write(bw, "import springfox.documentation.annotations.ApiIgnore;");
		write(bw, "import javax.servlet.http.HttpServletRequest;");
		write(bw, "import javax.servlet.http.HttpServletResponse;");
		write(bw, "import java.util.List;");
		bw.newLine();
		buildClassComment(bw, "[" + modelNote + "]控制器");
		write(bw, "@RestController");
		write(bw, "@RequestMapping(value = \"" + (versionInterfaceRouting.endsWith("/") ? versionInterfaceRouting.substring(1, versionInterfaceRouting.length() - 1) : versionInterfaceRouting) + "/" + processResultMapId(beanName) + "\")");
		write(bw, "@Api(tags = \"" + "[PC端]" + modelNote + "接口" + "\",  value = \"" + beanName + "Controller\")");
		write(bw, "public class " + beanName + "Controller extends BaseController {");
		bw.newLine();
		write(bw, "\t@Autowired");
		write(bw, "\tprivate " + serviceName + " " + processResultMapId(serviceName) + ";");
		bw.newLine();
		write(bw, "\t/**");
		write(bw, "\t * " + modelNote + "列表");
		write(bw, "\t */");
		write(bw, "\t@ApiOperation(value = \"" + modelNote + "列表\", notes = \"" + modelNote + "列表\")");
		write(bw, "\t@ApiImplicitParams({");

		for (int i = 1; i < columns.size(); i++) {
			String tempField = processField(columns.get(i));
			String type = processType(types.get(i));
			String comment = comments.get(i);
			String code = "\t\t\t@ApiImplicitParam(paramType = \"query\", name = \"" + tempField + "\", value = \"" + comment + "\", required = false, dataType = \"" + type + "\")";
			if (i < columns.size() - 1) {
				code += ",";
			}
			write(bw, code);
		}
		write(bw, "\t})");
		write(bw, "\t@RequestMapping(value = \"/list\", method = RequestMethod.GET)");
		write(bw, "\tpublic Result findAll(@ApiIgnore " + beanName + " " + processResultMapId(beanName) + ") {");
		write(bw, "\t\tList<" + beanName + "> list = " + processResultMapId(serviceName) + ".findList(" + processResultMapId(beanName) + ");");
		write(bw, "\t\treturn Result.ok(list);");
		write(bw, "\t}");
		bw.newLine();
		write(bw, "\t/**");
		write(bw, "\t * " + modelNote + "分页列表");
		write(bw, "\t */");
		write(bw, "\t@ApiOperation(value = \"" + modelNote + "分页列表\", notes = \"" + modelNote + "分页列表\")");
		write(bw, "\t@ApiImplicitParams({");
		write(bw, "\t\t\t@ApiImplicitParam(paramType = \"query\", name = \"pageNo\", value = \"当前页码\", required = true, dataType = \"String\"),");
		write(bw, "\t\t\t@ApiImplicitParam(paramType = \"query\", name = \"pageSize\", value = \"分页大小\", required = true, dataType = \"String\"),");

		for (int i = 1; i < columns.size(); i++) {
			String tempField = processField(columns.get(i));
			String type = processType(types.get(i));
			String comment = comments.get(i);
			String code = "\t\t\t@ApiImplicitParam(paramType = \"query\", name = \"" + tempField + "\", value = \"" + comment + "\", required = false, dataType = \"" + type + "\")";
			if (i < columns.size() - 1) {
				code += ",";
			}
			write(bw, code);
		}
		write(bw, "\t})");
		write(bw, "\t@RequestMapping(value = \"/page\", method = RequestMethod.GET)");
		write(bw, "\tpublic Result findPage(@ApiIgnore " + beanName + " " + processResultMapId(beanName) + ", HttpServletRequest request, HttpServletResponse response) {");
		write(bw, "\t\tPage<" + beanName + "> page = " + processResultMapId(serviceName) + ".findPage(new Page<" + beanName + ">(request, response), " + processResultMapId(beanName) + ");");
		write(bw, "\t\treturn Result.ok(page);");
		write(bw, "\t}");
		bw.newLine();
		write(bw, "\t/**");
		write(bw, "\t * " + modelNote + "详情");
		write(bw, "\t */");
		write(bw, "\t@ApiOperation(value = \"" + modelNote + "详情\", notes = \"根据id获取" + modelNote + "详情信息\")");
		write(bw, "\t@ApiImplicitParam(paramType = \"path\", name = \"id\", value = \"" + modelNote + "id\", required = true, dataType = \"int\")");
		write(bw, "\t@RequestMapping(value = \"/{id}\", method = RequestMethod.GET)");
		write(bw, "\tpublic Result get(@PathVariable(\"id\") Integer id) {");
		write(bw, "\t\t" + beanName + " " + processResultMapId(beanName) + " = " + processResultMapId(serviceName) + ".get(id);");
		write(bw, "\t\treturn Result.ok(" + processResultMapId(beanName) + ");");
		write(bw, "\t}");
		bw.newLine();
		write(bw, "\t/**");
		write(bw, "\t * " + modelNote + "保存");
		write(bw, "\t */");
		write(bw, "\t@ApiOperation(value = \"" + modelNote + "保存\", notes = \"" + modelNote + "如果数据库id自增就无需传入id\")");
		write(bw, "\t@RequestMapping(value = \"/save\", method = RequestMethod.POST)");
		write(bw, "\tpublic Result save(@RequestBody " + beanName + " " + processResultMapId(beanName) + ") {");
		write(bw, "\t\ttry {");
		write(bw, "\t\t\t" + processResultMapId(serviceName) + ".insert(" + processResultMapId(beanName) + ");");
		write(bw, "\t\t\treturn Result.ok(\"保存成功\");");
		write(bw, "\t\t} catch (Exception e) {");
		write(bw, "\t\t\tlogger.error(\"保存失败：\", e);");
		write(bw, "\t\t\treturn Result.error(\"保存异常\");");
		write(bw, "\t\t}");
		write(bw, "\t}");
		bw.newLine();
		write(bw, "\t/**");
		write(bw, "\t * " + modelNote + "修改");
		write(bw, "\t */");
		write(bw, "\t@ApiOperation(value = \"" + modelNote + "修改\", notes = \"" + modelNote + "修改\")");
		write(bw, "\t@RequestMapping(value = \"/update\", method = RequestMethod.POST)");
		write(bw, "\tpublic Result update(@RequestBody " + beanName + " " + processResultMapId(beanName) + ") {");
		write(bw, "\t\ttry {");
		write(bw, "\t\t\t" + processResultMapId(serviceName) + ".update(" + processResultMapId(beanName) + ");");
		write(bw, "\t\t\treturn Result.ok(\"修改成功\");");
		write(bw, "\t\t} catch (Exception e) {");
		write(bw, "\t\t\tlogger.error(\"修改失败：\", e);");
		write(bw, "\t\t\treturn Result.error(\"修改异常\");");
		write(bw, "\t\t}");
		write(bw, "\t}");
		bw.newLine();
		write(bw, "\t/**");
		write(bw, "\t * " + modelNote + "删除");
		write(bw, "\t */");
		write(bw, "\t@ApiOperation(value = \"" + modelNote + "删除\", notes = \"" + modelNote + "删除\")");
		write(bw, "\t@ApiImplicitParam(paramType = \"path\", name = \"id\", value = \"" + modelNote + "id\", required = true, dataType = \"int\")");
		write(bw, "\t@RequestMapping(value = \"/{id}\", method = RequestMethod.DELETE)");
		write(bw, "\tpublic Result delete(@PathVariable(\"id\") String ids) {");
		write(bw, "\t\ttry {");
		write(bw, "\t\t\t" + processResultMapId(serviceName) + ".deleteAll(ids, new " + beanName + "());");
		write(bw, "\t\t\treturn Result.ok(\"删除成功\");");
		write(bw, "\t\t} catch (Exception e) {");
		write(bw, "\t\t\tlogger.error(\"删除失败：\", e);");
		write(bw, "\t\t\treturn Result.error(\"删除异常\");");
		write(bw, "\t\t}");
		write(bw, "\t}");
		write(bw, "}");
		bw.flush();
		bw.close();
	}

	/**
	 * 字符串空判断, null返回false
	 *
	 * @param str
	 * @return
	 */
	private boolean stringNotNull(String str) {
		if (null != str && !"".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 输出换行封装
	 */
	private void write(BufferedWriter bw, String txt) throws IOException {
		bw.write(txt);
		bw.newLine();
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	private String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}
}
