package interface_adapter.DeleteEvent;

import use_case.DeleteEvent.DeleteEventInputBoundary;
import use_case.DeleteEvent.DeleteEventInputData;

public class DeleteEventController {
    final DeleteEventInputBoundary deleteEventInteractor;

    public DeleteEventController(DeleteEventInputBoundary deleteEventInteractor) {
        this.deleteEventInteractor = deleteEventInteractor;
    }
    public void execute(String itemaddress){
        DeleteEventInputData deleteEventInputData = new DeleteEventInputData(itemaddress);
        deleteEventInteractor.execute(deleteEventInputData);
    }
}
