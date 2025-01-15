package qe_dz4;

public class TimeResponse {
    private String updateTime;
    private String message;

    public TimeResponse() {
    }
    public TimeResponse(String message, String updateTime) {
        this.message = message;
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setPostedTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
