package cardinfo;

import java.util.Date;

public class CardLoginDetailsBean
{

    public CardLoginDetailsBean(int cardNo, Date loginDate, Date loginTime, Date logoutTime, String effectiveHours)
    {
        this.cardNo = cardNo;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.effectiveHours = effectiveHours;
    }

    public int getCardNo()
    {
        return cardNo;
    }

    public void setCardNo(int cardNo)
    {
        this.cardNo = cardNo;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime()
    {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime)
    {
        this.logoutTime = logoutTime;
    }

    public String getEffectiveHours()
    {
        return effectiveHours;
    }

    public void setEffectiveHours(String effectiveHours)
    {
        this.effectiveHours = effectiveHours;
    }

    public String toString()
    {
        return ( "CardNo :: "+cardNo+", login Date :: "+loginDate+", Login Time :: "+loginTime+", Logout Time :: "+logoutTime+", Effective Hours ::  "+effectiveHours);
    }

    private int cardNo;
    private Date loginDate;
    private Date loginTime;
    private Date logoutTime;
    private String effectiveHours;
}
