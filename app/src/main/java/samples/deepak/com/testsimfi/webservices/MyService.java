package samples.deepak.com.testsimfi.webservices;

/**
 * Created by deepak on 11/9/2017.
 */
public class MyService {



    public MyService(String stringName, int id,String stringColorCode, boolean booleanProgress,String stringStatus){

        this.strServiceName=stringName;
        this.intServiceId=id;
        this.stringStatus=stringStatus;
        this.booleanProgress=booleanProgress;
        this.stringColorCode=stringColorCode;



    }

    public String getStrServiceName() {
        return strServiceName;
    }

    public void setStrServiceName(String strServiceName) {
        this.strServiceName = strServiceName;
    }

    public int getIntServiceId() {
        return intServiceId;
    }

    public void setIntServiceId(int intServiceId) {
        this.intServiceId = intServiceId;
    }

    String strServiceName;
    int intServiceId;
    boolean booleanProgress;

    public String getStringColorCode() {
        return stringColorCode;
    }

    public void setStringColorCode(String stringColorCode) {
        this.stringColorCode = stringColorCode;
    }

    public boolean isBooleanProgress() {
        return booleanProgress;
    }

    public void setBooleanProgress(boolean booleanProgress) {
        this.booleanProgress = booleanProgress;
    }

    String stringColorCode;

    public String getStringStatus() {
        return stringStatus;
    }

    public void setStringStatus(String stringStatus) {
        this.stringStatus = stringStatus;
    }

    String stringStatus;

}
