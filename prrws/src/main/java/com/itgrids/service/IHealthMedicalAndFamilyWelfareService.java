package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.DiseasesVO;

public interface IHealthMedicalAndFamilyWelfareService {
	public List<DiseasesVO> getCaseCountDiseasesWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList);
	public List<DiseasesVO> getCaseCountLocationWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long scope,Long superLocationId);
	public List<DiseasesVO> getCaseCountDateWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,String rangeType);
}
