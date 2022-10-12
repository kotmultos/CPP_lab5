import manager.InputManager;

public class TaskPerformer {
    private String text;

    public TaskPerformer() {
        text = InputManager.getStringsFromConsole("Введіть стрічки тексту:");
    }

    public void performLab() {
        System.out.println("-------------");
        System.out.println(text);
    }
}
