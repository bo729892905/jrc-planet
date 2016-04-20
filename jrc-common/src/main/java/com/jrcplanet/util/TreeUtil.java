package com.jrcplanet.util;

import com.jrcplanet.model.easyui.Tree;
import com.jrcplanet.model.easyui.TreeNode;

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

    private static class TreeUtilFactory{
        private static TreeUtil instance = new TreeUtil();
    }

    public static TreeUtil getInstance() {
        return TreeUtilFactory.instance;
    }

    private Tree formatTree(List<?> list, String id, String text, List<?> children, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter) {
        Tree tree = new Tree();
        List<TreeNode> treeNodes = formatTreeList(list, id, text, children, treeChildrenGetter, treeNodeStateSetter);
        tree.setTreeNodeList(treeNodes);
        return tree;
    }

    /**
     * 格式化树列表
     * @param list 实体列
     * @param id 主键字段名
     * @param text 名称字段名
     * @param children 实体子集
     * @param treeChildrenGetter 实体查询子集的方法
     * @param treeNodeStateSetter 设置实体状态的方法
     * @return
     */
    private List<TreeNode> formatTreeList(List<?> list, String id, String text, List<?> children, TreeChildrenGetter treeChildrenGetter, TreeNodeStateSetter treeNodeStateSetter){
        List<TreeNode> treeNodes=new ArrayList<>();
        if(list!=null&&list.size()!=0){
            treeNodes.forEach(instance->{
                Class<?> class1=instance.getClass();
                TreeNode treeNode=formatTreeNode(class1,instance,id,text,children,treeChildrenGetter,treeNodeStateSetter);
                treeNodes.add(treeNode);
            });
        }
        return treeNodes;
    }

    /**
     * 实体格式化为树节点
     * @param class1 实体类型
     * @param instance 实体
     * @param id 主键字段名
     * @param text 名称字段名
     * @param children 实体子集
     * @param treeChildrenGetter 实体查询子集的方法
     * @param treeNodeStateSetter 设置实体状态的方法
     * @return
     */
    private TreeNode formatTreeNode(Class<?> class1, Object instance, String id, String text, List<?> children,TreeChildrenGetter treeChildrenGetter,TreeNodeStateSetter treeNodeStateSetter) {
        TreeNode treeNode=new TreeNode();
        try {
            //获取id的getter
            String id0=id.replaceFirst(id.substring(0, 1), id.substring(0, 1).toUpperCase());
            Method idMethod=class1.getMethod("get"+id0);

            //获取text的getter
            String text0=text.replaceFirst(text.substring(0, 1), text.substring(0, 1).toUpperCase());
            Method textMethod=class1.getMethod("get"+text0);

            String id1=(String) idMethod.invoke(instance);
            treeNode.setId(id1);
            String text1=(String) textMethod.invoke(instance);
            treeNode.setText(text1);

            if(treeChildrenGetter!=null&&children==null){
                children=treeChildrenGetter.getChildren(treeNode);
            }else{
                treeNode.setAttributes(instance);
            }

            List<TreeNode> treeNodeList= formatTreeList(children, id, text,treeChildrenGetter,treeNodeStateSetter);
            treeNode.setChildren(treeNodeList);

            if(treeNodeStateSetter!=null){
                boolean hasChildren=treeNodeStateSetter.hasChildren(treeNode);
                if(hasChildren)treeNode.setStates("closed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeNode;
    }

    /**
     * 获取并格式化完整的树（通过TreeNodeStateSetter修改节点状态）
     * @param list 实体列
     * @param id 主键字段名
     * @param text 名称字段名
     * @param treeChildrenGetter 实体查询子集的方法
     * @param treeNodeStateSetter 设置实体状态的方法
     * @return
     */
    private List<TreeNode> formatTreeList(List<?> list,String id,String text,TreeChildrenGetter treeChildrenGetter,TreeNodeStateSetter treeNodeStateSetter){
        return formatTreeList(list, id, text, null,treeChildrenGetter,treeNodeStateSetter);
    }
}
