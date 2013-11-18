package com.itgrids.voterdata.VO;

public class VoterInfo {
    private String voterId;
    private String voterName;
    private String guardianName;
    private String guardianRelation;
    private String houseNumber;
    private String sex;
    private String age;
    private String district;
    private String constituency;
    private String boothName;
    private String boothNo;
    private String constituencyId;
    private Long sNo;
    private String dupVoterId;
    
    public String getDupVoterId() {
		return dupVoterId;
	}

	public void setDupVoterId(String dupVoterId) {
		this.dupVoterId = dupVoterId;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoterInfo voterInfo = (VoterInfo) o;

        if (!age.equals(voterInfo.age)) return false;
        if (!boothName.equals(voterInfo.boothName)) return false;
        if (!boothNo.equals(voterInfo.boothNo)) return false;
        if (!constituency.equals(voterInfo.constituency)) return false;
        if (!district.equals(voterInfo.district)) return false;
        if (!guardianName.equals(voterInfo.guardianName)) return false;
        if (!guardianRelation.equals(voterInfo.guardianRelation)) return false;
        if (!houseNumber.equals(voterInfo.houseNumber)) return false;
        if (!sex.equals(voterInfo.sex)) return false;
        if (!voterId.equals(voterInfo.voterId)) return false;
        if (!voterName.equals(voterInfo.voterName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = voterId.hashCode();
        result = 31 * result + voterName.hashCode();
        result = 31 * result + guardianName.hashCode();
        result = 31 * result + guardianRelation.hashCode();
        result = 31 * result + houseNumber.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + district.hashCode();
        result = 31 * result + constituency.hashCode();
        result = 31 * result + boothName.hashCode();
        result = 31 * result + boothNo.hashCode();
        return result;
    }

    public String getBoothNo() {

        return boothNo;
    }

    public void setBoothNo(String boothNo) {
        this.boothNo = boothNo;
    }

    public String getDistrict() {

        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getBoothName() {
        return boothName;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }

    @Override
    public String toString() {
        return "VoterInfo{" +
                "voterId='" + voterId + '\'' +
                ", voterName='" + voterName + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", guardianRelation='" + guardianRelation + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", district='" + district + '\'' +
                ", constituency='" + constituency + '\'' +
                ", boothName='" + boothName + '\'' +
                ", boothNo='" + boothNo + '\'' +
                '}';
    }

    public String getVoterId() {

        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianRelation() {
        return guardianRelation;
    }

    public void setGuardianRelation(String guardianRelation) {
        this.guardianRelation = guardianRelation;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}
}
