package com.jrcplanet.util;

import com.jrcplanet.model.easyui.Tree;
import com.jrcplanet.model.easyui.TreeNode;
import com.jrcplanet.model.easyui.annotation.TreeId;
import com.jrcplanet.model.easyui.annotation.TreeText;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 树工具类
 * Created by rxb on 2016/4/20.
 */
public class TreeUtil {
    private TreeUtil() {

    }

    private static class TreeUtilFactory {
        private static TreeUtil instance = new TreeUtil();
    }

    public static TreeUtil getInstance() {
        return TreeUtilFactory.instance;
    }

    /**
     * 该方法只适用于根节点只有一个的格式化
     *
     * @param list
     * @param id
     * @param text
     * @param children
     * @param treeNodeStateSetter
     * @return
     */
    public Tree formatTree(List<?> list, String id, String text, List<?> children, TreeNodeStateSetter treeNodeStateSetter,boolean containAttr) {
        return formatTree(list, id, text, children, null, treeNodeStateSetter, 0, -1,containAttr);
    }

    /**
     * 该方法只适用于根节点只有一个的格式化
     *
     * @param list
     * @param children
     * @param treeNodeStateSetter
     * @return
     */
    public Tree formatTree(List<?> list, List<?> children, TreeNodeStateSetter treeNodeStateSetter,boolean containAttr) {
        return formatTree(list, children, null, treeNodeStateSetter, 0, -1,containAttr);
    }

    /**
     * 根据自定义的子节点获取方法来格式化树
     *
     * @param list
     * @param id
     * @param text
     * @param treeChildrenGetter
     * @param treeNodeStateSetter
     * @return
     */
    public Tree formatTree(List<?> list, String id, String text, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter,boolean containAttr) {
        return formatTree(list, id, text, null, treeChildrenGetter, treeNodeStateSetter, 0, -1,containAttr);
    }

    /**
     * 根据自定义的子节点获取方法来格式化树
     *
     * @param list
     * @param treeChildrenGetter
     * @param treeNodeStateSetter
     * @return
     */
    public Tree formatTree(List<?> list, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter,boolean containAttr) {
        return formatTree(list, null, treeChildrenGetter, treeNodeStateSetter, 0, -1,containAttr);
    }

    /**
     * 根据自定义的子节点获取方法来格式化树并返回固定级别
     *
     * @param list
     * @param id
     * @param text
     * @param treeChildrenGetter
     * @param treeNodeStateSetter
     * @param level               显示到的最低级别，0为根节点，每增加一级加1
     * @return
     */
    public Tree formatTree(List<?> list, String id, String text, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, boolean containAttr, int level) {
        return formatTree(list, id, text, null, treeChildrenGetter, treeNodeStateSetter, 0, level, containAttr);
    }

    /**
     * 根据自定义的子节点获取方法来格式化树并返回固定级别
     *
     * @param list
     * @param treeChildrenGetter
     * @param treeNodeStateSetter
     * @param level               显示到的最低级别，0为根节点，每增加一级加1
     * @return
     */
    public Tree formatTree(List<?> list, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, boolean containAttr, int level) {
        return formatTree(list, null, treeChildrenGetter, treeNodeStateSetter, 0, level, containAttr);
    }

    private Tree formatTree(List<?> list, String id, String text, List<?> children, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, int currentLevel, int level,boolean containAttr) {
        Tree tree = new Tree();
        List<TreeNode> treeNodes = formatTreeList(list, id, text, children, treeChildrenGetter, treeNodeStateSetter, currentLevel, level,containAttr);
        tree.setTreeNodeList(treeNodes);
        return tree;
    }

    private Tree formatTree(List<?> list, List<?> children, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, int currentLevel, int level,boolean containAttr) {
        Tree tree = new Tree();
        List<TreeNode> treeNodes = formatTreeList(list, children, treeChildrenGetter, treeNodeStateSetter, currentLevel, level,containAttr);
        tree.setTreeNodeList(treeNodes);
        return tree;
    }

    /**
     * 格式化树列表
     *
     * @param list                实体列
     * @param id                  主键字段名
     * @param text                名称字段名
     * @param children            实体子集
     * @param treeChildrenGetter  实体查询子集的方法
     * @param treeNodeStateSetter 设置实体状态的方法
     * @return
     */
    private List<TreeNode> formatTreeList(List<?> list, String id, String text, List<?> children, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, int currentLevel, int level,boolean containAttr) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (list != null) {
            list.forEach(instance -> {
                Class<?> class1 = instance.getClass();
                TreeNode treeNode = formatTreeNode(class1, instance, id, text, children, treeChildrenGetter, treeNodeStateSetter, currentLevel, level,containAttr);
                treeNodes.add(treeNode);
            });
        }
        return treeNodes;
    }

    /**
     * 格式化树列表
     *
     * @param list                实体列
     * @param children            实体子集
     * @param treeChildrenGetter  实体查询子集的方法
     * @param treeNodeStateSetter 设置实体状态的方法
     * @return
     */
    private List<TreeNode> formatTreeList(List<?> list, List<?> children, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, int currentLevel, int level,boolean containAttr) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (list != null) {
            list.forEach(instance -> {
                Class<?> class1 = instance.getClass();
                TreeNode treeNode = formatTreeNode(class1, instance, children, treeChildrenGetter, treeNodeStateSetter, currentLevel, level,containAttr);
                treeNodes.add(treeNode);
            });
        }
        return treeNodes;
    }

    /**
     * 实体格式化为树节点
     *
     * @param class1              实体类型
     * @param instance            实体
     * @param id                  主键字段名
     * @param text                名称字段名
     * @param children            实体子集
     * @param treeChildrenGetter  实体查询子集的方法
     * @param treeNodeStateSetter 设置实体状态的方法
     * @return
     */
    private TreeNode formatTreeNode(Class<?> class1, Object instance, String id, String text, List<?> children, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, int currentLevel, int level,boolean containAttr) {
        TreeNode treeNode = new TreeNode();
        try {
            Method idMethod = getGetterByField(id, class1);
            Object idVal = idMethod.invoke(instance);
            treeNode.setId(idVal==null?null:idVal.toString());

            Method textMethod = getGetterByField(text, class1);
            Object textVal = textMethod.invoke(instance);
            treeNode.setText(textVal==null?null:textVal.toString());

            //设置iconCls属性（如果没有iconCls属性将忽略）
            try {
                Method iconMethod = class1.getMethod("getIconCls");
                Object iconCls = iconMethod.invoke(instance);
                treeNode.setIconCls(iconCls==null?null:iconCls.toString());
            } catch (NoSuchMethodException e) {
                //ignore
            }

            if (containAttr) {
                treeNode.setAttributes(instance);
            }

            //当级别为-1时返回所有节点，否则返回level级节点，从1开始
            if (level == -1 || currentLevel < level) {
                if (treeChildrenGetter != null && children == null) {
                    children = treeChildrenGetter.getChildren(treeNode);
                }

                List<TreeNode> treeNodeList = formatTreeList(children, id, text, treeChildrenGetter, treeNodeStateSetter, ++currentLevel, level,containAttr);
                treeNode.setChildren(treeNodeList);
            }

            if (treeNodeStateSetter != null) {
                boolean hasChildren = children != null || treeNodeStateSetter.hasChildren(treeNode);
                if (hasChildren) treeNode.setStates("closed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeNode;
    }

    /**
     * 获取并格式化完整的树（通过TreeNodeStateSetter修改节点状态）
     *
     * @param list                实体列
     * @param id                  主键字段名
     * @param text                名称字段名
     * @param treeChildrenGetter  实体查询子集的方法
     * @param treeNodeStateSetter 设置实体状态的方法
     * @return
     */
    private List<TreeNode> formatTreeList(List<?> list, String id, String text, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, int currentLevel, int level,boolean containAttr) {
        return formatTreeList(list, id, text, null, treeChildrenGetter, treeNodeStateSetter, currentLevel, level,containAttr);
    }

    /**
     * 实体格式化为树节点
     *
     * @param class1              实体类型
     * @param instance            实体
     * @param children            实体子集
     * @param treeChildrenGetter  实体查询子集的方法
     * @param treeNodeStateSetter 设置实体状态的方法
     * @return
     */
    private TreeNode formatTreeNode(Class<?> class1, Object instance, List<?> children, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter, int currentLevel, int level,boolean containAttr) {
        String id = null, text = null;
        Field[] fields = class1.getDeclaredFields();
        for (Field field : fields) {
            if (id == null && field.isAnnotationPresent(TreeId.class)) {//获取id属性
                id = field.getName();
            }
            if (text == null && field.isAnnotationPresent(TreeText.class)) {//获取text属性
                text = field.getName();
            }
            if (id != null && text != null) {//id和text均有值时结束循环
                break;
            }
        }

        //TODO 异常点：如果id和text都为空应抛出异常

        return formatTreeNode(class1,instance, id, text, children, treeChildrenGetter, treeNodeStateSetter, currentLevel, level,containAttr);
    }

    /**
     * 根据属性名获取getter方法
     * @param methodName
     * @param cls
     * @return
     * @throws NoSuchMethodException
     */
    private Method getGetterByField(String methodName,Class<?> cls) throws NoSuchMethodException {
        String field = methodName.replaceFirst(methodName.substring(0, 1), methodName.substring(0, 1).toUpperCase());
        return cls.getMethod("get" + field);
    }
}
