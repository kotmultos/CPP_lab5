import manager.DisplayManager;
import manager.InputManager;
import manager.StringHelper;

import java.util.List;

public class TaskPerformer {
    private String text;

    public TaskPerformer() {
//        text = InputManager.getStringsFromConsole("Введіть стрічки тексту:");
        text = InputManager.getStringsFromFile("input.txt");
    }

    public void performLab() {
        DisplayManager.Print("Ввід користувача:", text);

        // remove odd whitespaces
        text = StringHelper.convertTextToNormalState(text);
        DisplayManager.Print("Нормалізований текст:", text);

        // split on sentences
        List<String> sentences = StringHelper.splitOnSentences(text);
        DisplayManager.Print("Текст, поділений на речення", sentences);



    }
}
