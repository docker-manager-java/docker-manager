package com.musket.docker.manager.util.httpclientutil.common.util;

import java.util.ArrayList;
import java.util.List;

/***
 * 查询参数对象模型
 * @author dongwenfeng
 *
 */
public class QueryOptions {
    //每页显示结果数,为0时表示不分页，默认为0及用户不设置分页查询条件时，查询出所有的记录
    private int PageSize = 0;
    //当前查询页,当PageSize设置为0时,该参数无效
    private int PageIndex = 1;
    //排序属性集合
    private String[] SortFields;
    //排序方式集合
    private boolean[] SortTypes;
    //是否包含服务【资源化查询时使用】
    private boolean includeService = false;
    //分组字段【资源化查询时使用】，仅支持一个字段
    private String groupFiled;

	/* 以下参数不在进行支持，QueryOptions只提供分页的排序信息
	 * 查询参数等信息不在由外部进行控制
	 *
	//查询返回结果字段集合（暂不支持）
	private String[] ResultFlds;
	//查询的Key值集合
	private String[] QueryKeys;
	//查询的Value值集合
	private Object[] QueryValues;
	//查询条件的操作条件
	private SqlOper[] SqlOpers;
	*/

    public QueryOptions(){};
    /***
     * 查询参数对象模型
     * @param pageSize 每页展示大小
     * @param pageIndex 当前查询第几页
     * @param sortFields 排序字段集合
     * @param SortTypes  排序类型集合，需要和排序字段集合对应，true为Asc
     */
    public QueryOptions(int pageSize, int pageIndex, String[] sortFields,
                        boolean[] SortTypes) {
        this.PageSize=pageSize;
        this.PageIndex=pageIndex;
        this.SortFields=sortFields;
        this.SortTypes=SortTypes;
    }

    /**
     * 查询参数对象模型
     * @param pageSize  每页展示大小
     * @param pageIndex  当前查询第几页
     * @param sortFields  排序字段集合
     * @param sortTypes    排序类型集合，需要和排序字段集合对应，true为Asc
     * @param groupFiled   分组字段【资源化查询时使用】，仅支持一个字段
     */
    public QueryOptions(int pageSize, int pageIndex, String[] sortFields, boolean[] sortTypes, boolean includeService, String groupFiled) {
        PageSize = pageSize;
        PageIndex = pageIndex;
        SortFields = sortFields;
        SortTypes = sortTypes;
        this.includeService = includeService;
        this.groupFiled = groupFiled;
    }

    /**
     *每页显示记录数
     * @return
     */
    public int getPageSize() {
        return PageSize;
    }
    /***
     * 每页显示记录数，默认为0，查询出所有的记录
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }
    /**
     *当前查询分页
     * @return
     */
    public int getPageIndex() {
        return PageIndex;
    }
    /**
     * 设置当前查询分页
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }
    /**
     *  结果排序字段集合
     * @return
     */
    public String[] getSortFields() {
        return SortFields;
    }
    /**
     * 设置查询结果排序字段集合
     * @param sortFields
     */
    public void setSortFields(String[] sortFields) {
        SortFields = sortFields;
    }
    /**
     * 查询排序结合
     * @return
     */
    public boolean[] getSortTypes() {
        return SortTypes;
    }
    /**
     * 设置查询排序类型，和排序字段相对应，True表示asc
     * @param sortTypes
     */
    public void setSortTypes(boolean[] sortTypes) {
        SortTypes = sortTypes;
    }

    /**
     * 获取查询排序语句
     * @return
     */
    public String getOrderBySql(){
        StringBuilder sb=new StringBuilder();
        if(null!=this.SortFields&&this.SortFields.length>0){
            sb.append(" Order by ");
            for(int i=0;i<this.SortFields.length;i++){
                sb.append(" ").append(SortFields[i]).append(" ").append(getOrderKey(this.SortTypes[i]));
                if(i != this.SortFields.length - 1) {
                    sb.append(" , ");
                }
            }
        }
        return sb.toString();
    }

    /***
     * 获取查询排序关键字
     * @return
     */
    private String getOrderKey(boolean blnAsc){
        return blnAsc==true?"ASC":"DESC";
    }

    /***
     * 获取查询语句，对应的查询值以？替换
     * @return
     */
//	public String getQuerySql(){
//		String strSql="";
//		if(null!=QueryKeys&&QueryKeys.length>0){
//			String strQueryItem="";
//			for(int i=0,len=QueryKeys.length;i<len;i++){
//				strQueryItem="";
//				if(SqlOpers[i].getValue()==SqlOper.IN.getValue()){
//					strQueryItem=QueryKeys[i]+" "+SqlOpers[i].getOper() +" ( ? )";
//				}else{
//					strQueryItem=QueryKeys[i]+" "+SqlOpers[i].getOper() +" ?";
//				}
//				if(!strSql.isEmpty()){
//					strSql=strSql+" AND ";
//				}
//				strSql=strSql+strQueryItem;
//			}
//		}
//		return strSql;
//	}

    private List<String> getSortFlds(){
        List<String> sortFlds=new ArrayList<String>();
        String[] flds=this.getSortFields();
        if(null!=flds&&flds.length>0){
            for(String str:flds){
                sortFlds.add(str);
            }
        }
        return sortFlds;
    }

    private List<Boolean> getSoTypes(){
        List<Boolean> sortTypes=new ArrayList<Boolean>();
        boolean [] types=this.getSortTypes();
        if(null!=types&&types.length>0){
            for(boolean bln:types){
                sortTypes.add(bln);
            }
        }
        return sortTypes;
    }
    /***
     * 为当前查询条件增加才查询排序信息
     * @param sortFlds
     * @param sortTypes
     */
    public void addOrderFldsInfo(String[] sortFlds,boolean[] sortTypes){
        if(null!=sortFlds&&null!=sortTypes&&sortFlds.length>0&&sortTypes.length>0){
            List<String> currentSortFlds=this.getSortFlds();
            List<Boolean> currentSortTps=this.getSoTypes();
            for(int i =0,len=sortFlds.length;i<len;i++){
                String strSortFld=sortFlds[i];
                boolean blnSortType=sortTypes[i];
                if(!currentSortFlds.contains(strSortFld)){
                    currentSortFlds.add(strSortFld);
                    currentSortTps.add(blnSortType);
                }
            }
            //将增加的排序信息，重新设置回排序信息中
            this.SortFields=currentSortFlds.toArray(new String[0]);
            Boolean[] blnObjs=currentSortTps.toArray(new Boolean[0]);
            boolean[] blns=new boolean[currentSortFlds.size()];
            for(int i=0,len=blnObjs.length;i<len;i++){
                blns[i]=(boolean)blnObjs[i];
            }
            this.SortTypes=blns;
        }
    }

    public boolean isIncludeService() {
        return includeService;
    }

    public void setIncludeService(boolean includeService) {
        this.includeService = includeService;
    }

    public String getGroupFiled() {
        return groupFiled;
    }

    public void setGroupFiled(String groupFiled) {
        this.groupFiled = groupFiled;
    }
}
