package controller;

public enum Action {
    REGISTRATION("1"),
    AUTHORIZATION("2"),
    RECOVER_PASSWORD("3"),
    EXIT("4");

    private final String actionCode;

    Action(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public static Action fromCode(String code) {
        for (Action action : Action.values()) {
            if (action.getActionCode().equals(code)) {
                return action;
            }
        }
        return null;
    }
}