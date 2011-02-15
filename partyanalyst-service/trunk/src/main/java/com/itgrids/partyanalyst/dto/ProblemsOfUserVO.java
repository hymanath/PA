package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ProblemsOfUserVO {

		private List<ProblemBeanVO> problemsByUser;
		private List<SelectOptionVO> problemRegionScopes;
		private List<SelectOptionVO> problemTypes;
		
		private Long totalResultsCount;
		
		private ResultStatus resultStatus;
		
		public ProblemsOfUserVO(){
			
		}
		
		public ProblemsOfUserVO(List<ProblemBeanVO> problemsByUser,
				List<SelectOptionVO> problemRegionScopes) {
			this.problemsByUser = problemsByUser;
			this.problemRegionScopes = problemRegionScopes;
		}

		public List<ProblemBeanVO> getProblemsByUser() {
			return problemsByUser;
		}
		
		public void setProblemsByUser(List<ProblemBeanVO> problemsByUser) {
			this.problemsByUser = problemsByUser;
		}
		
		public List<SelectOptionVO> getProblemRegionScopes() {
			return problemRegionScopes;
		}
		
		public void setProblemRegionScopes(List<SelectOptionVO> problemRegionScopes) {
			this.problemRegionScopes = problemRegionScopes;
		}
		
		public List<SelectOptionVO> getProblemTypes() {
			return problemTypes;
		}

		public void setProblemTypes(List<SelectOptionVO> problemTypes) {
			this.problemTypes = problemTypes;
		}

		public ResultStatus getResultStatus() {
			return resultStatus;
		}

		public void setResultStatus(ResultStatus resultStatus) {
			this.resultStatus = resultStatus;
		}

		public Long getTotalResultsCount() {
			return totalResultsCount;
		}

		public void setTotalResultsCount(Long totalResultsCount) {
			this.totalResultsCount = totalResultsCount;
		}

}
