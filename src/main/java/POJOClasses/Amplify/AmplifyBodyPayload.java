package POJOClasses.Amplify;

import java.util.List;

public class AmplifyBodyPayload {
    private List<Messages> messages;
    private String model_id;

    public List<Messages> getMessages() {
        return messages;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }
}
