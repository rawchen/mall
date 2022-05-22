package com.rawchen.mall.ware.vo;

import lombok.Data;
/**
 * <p>Title: PurchaseItemDoneVo</p>
 * Description：采购项
 * date：2022/1/6 23:23
 */
@Data
public class PurchaseItemDoneVo {
	/**
	 * "itemId":1,"status":3,"reason":"",
	 * "itemId":3,"status":4,"reason":"无货"
	 */
	private Long itemId;

    private Integer status;

    private String reason;
}
