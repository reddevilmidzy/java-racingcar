package view.input;


public enum ErrorMessages {
    EMPTY_INPUT("입력값이 없습니다."),
    NOT_PROPER_CAR_NAME_LENGTH(
        "자동차 이름 길이는 " + Validator.MIN_CAR_NAME_LENGTH + "자 이상, " + Validator.MAX_CAR_NAME_LENGTH + "자 이하여야합니다."),
    CONTAINS_DELIMITER("자동차 이름은 '-'를 포함할 수 없습니다"),
    NOT_PROPER_COUNT("올바르지 않은 시도횟수입니다.(1 ~ 1,000,000,000)");
    private final String content;

    ErrorMessages(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}