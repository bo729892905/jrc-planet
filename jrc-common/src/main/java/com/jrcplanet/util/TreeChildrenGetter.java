package com.jrcplanet.util;

import com.jrcplanet.model.easyui.TreeNode;

import java.util.List;

public interface TreeChildrenGetter {
	List<?> getChildren(TreeNode treeNode);
}
