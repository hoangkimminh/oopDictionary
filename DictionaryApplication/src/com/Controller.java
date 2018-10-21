package com;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {


    //root - parent
    public AnchorPane background;


    //Animation - TranslateTransition
    private TranslateTransition showAddBoxAnimation;
    private TranslateTransition hideAddBoxAnimation;

    private TranslateTransition showDeleteBoxAnimation;
    private TranslateTransition hideDeleteBoxAnination;

    private TranslateTransition showEditBoxAnimation;
    private TranslateTransition hideEditBoxAnination;

    private TranslateTransition showSettingBoxAnimation;
    private TranslateTransition hideSettingBoxAnimation;

    private TranslateTransition showTranslateBoxAnimation;
    private TranslateTransition hideTranslateBoxAnimation;

    //tool button
    public Button addButton;
    public Button editButton;
    public Button deleteButton;
    public Button settingButton;
    public Button translateButton;


    //toolbox & status
    public AnchorPane addBox;
    public AnchorPane deleteBox;
    public AnchorPane editBox;
    public AnchorPane settingBox;
    public AnchorPane translateBox;

    public boolean isShowAddBox;
    public boolean isShowDeleteBox;
    public boolean isShowEditBox;
    public boolean isShowSettingBox;
    public boolean isShowTranslateBox;

    //dictionary and list view
    public ListView<Word> listView;
    public TextField searchField;
    private Dictionary dictionary = new Dictionary();
    private ObservableList<Word> observableList;


    //word explain lable
    public Label wordTargetLable;
    public TextArea wordExplainLable;

    //Voice
    public Voice voice;

    //Translator
    Translator translator = new Translator();

    //run after main
    public void initialize() {

        dictionary.insertFromFile();
        observableList = FXCollections.observableList(dictionary.getWords());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setItems(observableList);



        background.getStylesheets().clear();
        background.getStylesheets().add("lightStyle.css");
        lightButton.setSelected(true);

        System.setProperty("mbrola.base", "mbrola");
        voice = VoiceManager.getInstance().getVoice("mbrola_us1");
        voice.allocate();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //addBox animation
                showAddBoxAnimation = new TranslateTransition(Duration.seconds(0.5), addBox);
                showAddBoxAnimation.setToX(addBox.getWidth());
                hideAddBoxAnimation = new TranslateTransition(Duration.seconds(0.2), addBox);
                hideAddBoxAnimation.setToX(0);
                //deleteBox animation
                showDeleteBoxAnimation = new TranslateTransition(Duration.seconds(0.5), deleteBox);
                showDeleteBoxAnimation.setToX(deleteBox.getWidth());
                hideDeleteBoxAnination = new TranslateTransition(Duration.seconds(0.2), deleteBox);
                hideDeleteBoxAnination.setToX(0);
                //editBox animation
                showEditBoxAnimation = new TranslateTransition(Duration.seconds(0.5), editBox);
                showEditBoxAnimation.setToX(editBox.getWidth());
                hideEditBoxAnination = new TranslateTransition(Duration.seconds(0.2), editBox);
                hideEditBoxAnination.setToX(0);
                //settingBox animation
                showSettingBoxAnimation = new TranslateTransition(Duration.seconds(0.5), settingBox);
                showSettingBoxAnimation.setToX(settingBox.getWidth());
                hideSettingBoxAnimation = new TranslateTransition(Duration.seconds(0.2), settingBox);
                hideSettingBoxAnimation.setToX(0);
                //translateBox animation
                showTranslateBoxAnimation = new TranslateTransition(Duration.seconds(0.5), translateBox);
                showTranslateBoxAnimation.setToX(translateBox.getWidth());
                hideTranslateBoxAnimation = new TranslateTransition(Duration.seconds(0.2), translateBox);
                hideTranslateBoxAnimation.setToX(0);
            }
        });
    }


    //window handle
    public void closeWindow() {
        Stage stage = (Stage) background.getScene().getWindow();
        stage.close();
    }
    public void hideWindow() {
        Stage stage = (Stage) background.getScene().getWindow();
        stage.setIconified(true);
    }


    //search word and show on list view
    public void search() {
        refreshListView();
        String str = searchField.getText();
        for (int i = 0; i < dictionary.getWords().size(); i++) {
            if (dictionary.getWords().get(i).getWordTarget().toLowerCase().indexOf(str.toLowerCase()) == 0) {
                listView.scrollTo(i);
                listView.getSelectionModel().select(i);
                return;
            }
        }
    }
    public void refreshListView() {
        listView.setItems(null);
        listView.setItems(observableList);
        listView.refresh();
        listView.scrollTo(0);
        listView.getSelectionModel().selectFirst();
    }
    public void printExplainWord() {
        Word temp = listView.getSelectionModel().getSelectedItem();
        wordExplainLable.setText(temp.getWordExplain());
        wordTargetLable.setText(temp.getWordTarget());
    }
    public void chooseFirstWord() {
        listView.requestFocus();
        if (listView.getSelectionModel().getSelectedItem() == null) {
            listView.getSelectionModel().selectFirst();
        }
        Word temp = listView.getSelectionModel().getSelectedItem();
        if (temp.getWordTarget() != searchField.getText()) {
            search();
        }
        wordExplainLable.setText(temp.getWordExplain());
        wordTargetLable.setText(temp.getWordTarget());
    }


    //handle all tool box
    public void hideAllToolBox() {
        isShowAddBox = false;
        isShowEditBox = false;
        isShowDeleteBox = false;
        isShowSettingBox = false;
        isShowTranslateBox = false;
        showSettingBoxAnimation.stop();
        showAddBoxAnimation.stop();
        showEditBoxAnimation.stop();
        showDeleteBoxAnimation.stop();
        showTranslateBoxAnimation.stop();
        hideAddBoxAnimation.play();
        hideEditBoxAnination.play();
        hideDeleteBoxAnination.play();
        hideSettingBoxAnimation.play();
        hideTranslateBoxAnimation.play();
        changeSidebarColorWhenClose();
    }


    //add box handle
    public TextField addWordTargetTextField;
    public TextField addWordExplainTextField;
    public Text addMessageText;

    public void clearAddBoxText() {
        addMessageText.setText("");
        addWordExplainTextField.setText("");
        addWordTargetTextField.setText("");
    }
    public void showAddBox() {
        if (!isShowAddBox) {
            hideAllToolBox();
            isShowAddBox = true;
            clearAddBoxText();
            changeSidebarColorWhenOpen(addButton);
            showAddBoxAnimation.play();
        } else {
            hideAllToolBox();
        }
    }
    public void addWord() {
        String newWordTarget = addWordTargetTextField.getText();
        String newWordExplain = addWordExplainTextField.getText();
        if (newWordTarget.equals("") || newWordExplain.equals("")) {
            addMessageText.setText("Không được điền trống");
            return;
        }
        clearAddBoxText();
        if (dictionary.add(newWordTarget, "", "", newWordExplain)) {
            addMessageText.setText("Thêm từ thành công");
            search();
        } else {
            addMessageText.setText("Đã có trong từ điển");
        }

    }


    //delete box handle
    public TextField deleteTextField;
    public Text deleteMessageText;

    public void showDeleteBox() {
        if (!isShowDeleteBox) {
            hideAllToolBox();
            isShowDeleteBox = true;
            changeSidebarColorWhenOpen(deleteButton);
            showDeleteBoxAnimation.play();
            deleteTextField.setText(wordTargetLable.getText());
        } else {
            hideAllToolBox();
        }
    }
    public void deleteWord() {
        String string = deleteTextField.getText();
        if (string.equals("")) {
            deleteMessageText.setText("Không được điền trống");
            return;
        }
        if (dictionary.delete(string)) {
            deleteMessageText.setText("Xóa từ thành công");
            deleteTextField.setText("");
            wordTargetLable.setText("Dictionary");
            wordExplainLable.setText("");
            search();
        } else {
            deleteMessageText.setText("Không tìm thấy từ");
        }
    }


    //edit box handle
    public TextField editWordTargetTextField;
    public TextField editWordExplainTextField;
    public TextField editTypeTextField;
    public TextField editPronounceTextField;
    public Text editMessageText;

    public void clearEditBoxText() {
        editWordTargetTextField.setText("");
        editWordExplainTextField.setText("");
        editTypeTextField.setText("");
        editPronounceTextField.setText("");
    }
    public void showEditBox() {
        if (!isShowEditBox) {
            hideAllToolBox();
            isShowEditBox = true;
            changeSidebarColorWhenOpen(editButton);
            showEditBoxAnimation.play();
            clearEditBoxText();
            editWordTargetTextField.setText(wordTargetLable.getText());
            editMessageText.setText("");
        } else {
            hideAllToolBox();
        }
    }
    public void editWord() {
        String wordTarget = editWordTargetTextField.getText();
        String wordExplain = editWordExplainTextField.getText();
        if (wordTarget.equals("")) {
            editMessageText.setText("Không được nhập trống từ cần sửa");
        } else if (wordExplain.equals("")) {
            editMessageText.setText("Không được nhập trống nghĩa");
        } else {
            if (dictionary.change(wordTarget, editTypeTextField.getText(), editPronounceTextField.getText(), wordExplain)) {
                search();           //refresh ListView
                editMessageText.setText("Sửa từ thành công");
                clearEditBoxText();
            } else {
                editMessageText.setText("Không tìm thấy từ");
            }
        }

    }

    //settings box handle
    //Change theme
    public RadioButton lightButton;
    public RadioButton darkButton;
    public RadioButton lovelyButton;

    public void changeSidebarColorWhenOpen(Button currentButton) {
        if (lightButton.isSelected()) {
            currentButton.setStyle("-fx-background-color: #dfe2e5");
        } else if (darkButton.isSelected()) {
            currentButton.setStyle("-fx-background-color: #242424");
        } else if (lovelyButton.isSelected()) {
            currentButton.setStyle("-fx-background-color: #ffdeec");
        }
    }
    public void changeSidebarColorWhenClose() {
        if (lightButton.isSelected()) {
            addButton.setStyle("-fx-background-color: #9bb6d5");
            editButton.setStyle("-fx-background-color: #9bb6d5");
            deleteButton.setStyle("-fx-background-color: #9bb6d5");
            settingButton.setStyle("-fx-background-color: #9bb6d5");
            translateButton.setStyle("-fx-background-color: #9bb6d5");
        } else if (darkButton.isSelected()) {
            addButton.setStyle("-fx-background-color: #040404");
            editButton.setStyle("-fx-background-color: #040404");
            deleteButton.setStyle("-fx-background-color: #040404");
            settingButton.setStyle("-fx-background-color: #040404");
            translateButton.setStyle("-fx-background-color: #040404");
        } else if (lovelyButton.isSelected()) {
            addButton.setStyle("-fx-background-color: #ff9893");
            editButton.setStyle("-fx-background-color: #ff9893");
            deleteButton.setStyle("-fx-background-color: #ff9893");
            settingButton.setStyle("-fx-background-color: #ff9893");
            translateButton.setStyle("-fx-background-color: #ff9893");
        }
    }
    public void setLightTheme() {
        lightButton.setSelected(true);
        darkButton.setSelected(false);
        lovelyButton.setSelected(false);
        background.getStylesheets().clear();
        background.getStylesheets().add("lightStyle.css");
        changeSidebarColorWhenClose();
        changeSidebarColorWhenOpen(settingButton);
    }
    public void setDarkTheme() {
        darkButton.setSelected(true);
        lightButton.setSelected(false);
        lovelyButton.setSelected(false);
        background.getStylesheets().clear();
        background.getStylesheets().add("darkStyle.css");
        changeSidebarColorWhenClose();
        changeSidebarColorWhenOpen(settingButton);
    }
    public void setLovelyTheme() {
        lovelyButton.setSelected(true);
        lightButton.setSelected(false);
        darkButton.setSelected(false);
        background.getStylesheets().clear();
        background.getStylesheets().add("lovelyStyle.css");
        changeSidebarColorWhenClose();
        changeSidebarColorWhenOpen(settingButton);
    }
    public void showSettingBox() {
        if (!isShowSettingBox) {
            hideAllToolBox();
            isShowSettingBox = true;
            changeSidebarColorWhenOpen(settingButton);
            showSettingBoxAnimation.play();
        } else {
            hideAllToolBox();
        }
    }

    //translate box handle
    public TextArea translateArea;

    public void showTranslateBox() {
        if (!isShowTranslateBox) {
            hideAllToolBox();
            isShowTranslateBox = true;
            changeSidebarColorWhenOpen(translateButton);
            showTranslateBoxAnimation.play();
        } else {
            hideAllToolBox();
        }
    }
    public void translateParagraph() {
        new Thread() {
            public void run() {
                try {
                    String[] arr = translateArea.getText().replace("\n", "<>").replace(".", "<.>").split("<.>");
                    String result = "";
                    for (String string : arr) {
                        result += translator.callUrlAndParseResult("en", "vi", string).replace("<> ", "\n") + ". ";
                    }
                    wordExplainLable.setText(result);
                } catch (Exception e) {
                    wordExplainLable.setText("Kiểm tra lại kết nối");
                }
            }
        }.start();
    }


    //audio function
    public void playSound() {
        new Thread(){
            public void run(){
                voice.speak(wordTargetLable.getText());
            }
        }.start();
    }
}
