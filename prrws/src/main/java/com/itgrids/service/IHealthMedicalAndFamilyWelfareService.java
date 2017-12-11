package com.itgrids.service;

import java.util.List;
import java.util.Set;

import com.itgrids.dto.DiseasesVO;
import com.itgrids.dto.LocationIdNameVO;

public interface IHealthMedicalAndFamilyWelfareService {
	public List<DiseasesVO> getCaseCountDiseasesWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,String type);
	public List<DiseasesVO> getCaseCountLocationWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long scope,Long superLocationId,String type,Long constituencyId);
	public List<DiseasesVO> getCaseCountDateWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,String rangeType,String type);
	public List<DiseasesVO> getLocationDtlsRankWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long minVal,Long maxVal,String type);
	public Set<LocationIdNameVO> getAllSubLocationsBySuperLocationId(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long superLocationId,String type);
	public DiseasesVO getCaseCountDiseasesFrWeekAndMonth(List<Long> diseasesIdList,List<Long> deptIdList);
}
