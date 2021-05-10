package pl.kodokan.fcp.server.dto;

public class DisciplineAdviceDTO {
    private String errorMsg;
    private String errorClass;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getLocalizedMsg() {
        return errorClass;
    }

    public void setLocalizedMsg(String localizedMsg) {
        this.errorClass = localizedMsg;
    }
}

