package com.chatmrkhoi.chatmrkhoi.design.state;
import lombok.Setter;

@Setter
public class ContextUser {

    private IUserState state;
    public ContextUser() {
    }
    public void ChangeState(IUserState state) {
        this.state = state;
    }

    public void handleStatus()
    {
        state.handleStatus();
        state.resetData();
    }

    public String getStatus()
    {
       return state.getStatus();
    }
}
