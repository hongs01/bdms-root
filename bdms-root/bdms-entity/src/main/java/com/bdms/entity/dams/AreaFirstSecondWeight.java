package com.bdms.entity.dams;

public class AreaFirstSecondWeight implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5080583075852547903L;
	
	/*area表*/
	private Integer a_id;				//区域Id
	private String a_areaName;		//区域名称
	private String a_area;			//该区域所归属的行政区域
	private Double a_totalMark;	
	private Integer a_alarmLevel;
	private Integer a_alarmCount;
	
	/*firstWeight表*/
	private Integer f_areaId;
	private String f_typeName; 		//一级计分项名称
	private String f_typeCode; 		//一级计分项对应的码值	
	private Double f_firstMark;		//一级计分项对应的权重值
	private Integer f_alarmCount;
	private Integer f_alarmLevel;
	
	/*firstWeight表*/
	private Integer s_typeId;		//对应firstWeight表中的主键
	private String s_stationId;			
	private String s_name;	
	private Integer s_peopleNum;		
	private Integer s_alarmLevel; 		
	private Double s_secondMark;
	
	//默认构造函数
	public AreaFirstSecondWeight() {
	}
	public AreaFirstSecondWeight(Integer a_id,String a_areaName,String a_area,Double a_totalMark,Integer a_alarmLevel,Integer a_alarmCount,
			Integer f_areaId,String f_typeName,String f_typeCode,Double f_firstMark,Integer f_alarmCount,Integer f_alarmLevel,
			Integer s_typeId,String s_stationId,String s_name,Integer s_alarmLevel,Double s_secondMark ,Integer s_peopleNum){
		
		this.a_id = a_id;
		this.a_areaName = a_areaName;
		this.a_area = a_area;
		this.a_totalMark = a_totalMark;
		this.a_alarmCount = a_alarmCount;
		this.a_alarmLevel = a_alarmLevel;
		
		this.f_areaId = f_areaId;
		this.f_typeCode = f_typeCode;
		this.f_firstMark = f_firstMark;
		this.f_typeName = f_typeName;
		this.f_alarmCount = f_alarmCount;
		this.f_alarmLevel = f_alarmLevel;
		
		this.s_typeId = s_typeId;
		this.s_stationId = s_stationId;
		this.s_name = s_name;
		this.s_peopleNum = s_peopleNum;
		this.s_alarmLevel = s_alarmLevel;
		this.s_secondMark = s_secondMark;
		
	}
	
	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}

	public String getA_areaName() {
		return a_areaName;
	}

	public void setA_areaName(String a_areaName) {
		this.a_areaName = a_areaName;
	}

	public String getA_area() {
		return a_area;
	}

	public void setA_area(String a_area) {
		this.a_area = a_area;
	}

	public Double getA_totalMark() {
		return a_totalMark;
	}

	public void setA_totalMark(Double a_totalMark) {
		this.a_totalMark = a_totalMark;
	}

	public Integer getA_alarmLevel() {
		return a_alarmLevel;
	}

	public void setA_alarmLevel(Integer a_alarmLevel) {
		this.a_alarmLevel = a_alarmLevel;
	}

	public Integer getA_alarmCount() {
		return a_alarmCount;
	}

	public void setA_alarmCount(Integer a_alarmCount) {
		this.a_alarmCount = a_alarmCount;
	}

	public Integer getF_areaId() {
		return f_areaId;
	}

	public void setF_areaId(Integer f_areaId) {
		this.f_areaId = f_areaId;
	}

	public String getF_typeName() {
		return f_typeName;
	}

	public void setF_typeName(String f_typeName) {
		this.f_typeName = f_typeName;
	}

	public String getF_typeCode() {
		return f_typeCode;
	}

	public void setF_typeCode(String f_typeCode) {
		this.f_typeCode = f_typeCode;
	}

	public Double getF_firstMark() {
		return f_firstMark;
	}

	public void setF_firstMark(Double f_firstMark) {
		this.f_firstMark = f_firstMark;
	}

	public Integer getF_alarmCount() {
		return f_alarmCount;
	}

	public void setF_alarmCount(Integer f_alarmCount) {
		this.f_alarmCount = f_alarmCount;
	}

	public Integer getF_alarmLevel() {
		return f_alarmLevel;
	}

	public void setF_alarmLevel(Integer f_alarmLevel) {
		this.f_alarmLevel = f_alarmLevel;
	}

	public Integer getS_typeId() {
		return s_typeId;
	}

	public void setS_typeId(Integer s_typeId) {
		this.s_typeId = s_typeId;
	}

	public String getS_stationId() {
		return s_stationId;
	}

	public void setS_stationId(String s_stationId) {
		this.s_stationId = s_stationId;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public Integer getS_peopleNum() {
		return s_peopleNum;
	}

	public void setS_peopleNum(Integer s_peopleNum) {
		this.s_peopleNum = s_peopleNum;
	}

	public Integer getS_alarmLevel() {
		return s_alarmLevel;
	}

	public void setS_alarmLevel(Integer s_alarmLevel) {
		this.s_alarmLevel = s_alarmLevel;
	}

	public Double getS_secondMark() {
		return s_secondMark;
	}

	public void setS_secondMark(Double s_secondMark) {
		this.s_secondMark = s_secondMark;
	}


	
}
