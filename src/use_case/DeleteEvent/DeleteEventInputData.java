package use_case.DeleteEvent;

public class DeleteEventInputData {
    public String getItemAddress() {
        return itemAddress;
    }

    final private String itemAddress;

    public DeleteEventInputData(String itemAddress) {
        this.itemAddress = itemAddress;
    }
}
