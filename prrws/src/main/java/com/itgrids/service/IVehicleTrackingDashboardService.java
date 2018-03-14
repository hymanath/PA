package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.VehicleTrackingVO;

public interface IVehicleTrackingDashboardService {
	
	public VehicleTrackingVO getVehicleTrackingOverviewDetails();
	public VehicleTrackingVO getOnFieldVehiclesDetails();
	public List<VehicleTrackingVO> getDayWiseOffFieldVehicleDetails();

}
