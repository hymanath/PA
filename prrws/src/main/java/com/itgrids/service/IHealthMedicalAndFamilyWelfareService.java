package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.DiseasesVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;

public interface IHealthMedicalAndFamilyWelfareService {
	public List<DiseasesVO> getCaseCountDiseasesWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList);
	public List<DiseasesVO> getCaseCountLocationWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long scope,Long superLocationId);
	public List<DiseasesVO> getCaseCountDateWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,String rangeType);
	public List<DiseasesVO> getLocationDtlsRankWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long minVal,Long maxVal);
	public List<LocationFundDetailsVO> getAllSubLocationsBySuperLocationId(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long superLocationId);
}
