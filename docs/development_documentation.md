**Table of Conetents**

- [Introduction](#introduction)
- [Build Guide](#build-guide)
- [Design](#design)

# Introduction

This document contains most of the important information related to JSoundBoard including how to build the application, the design, and development conventions.

# Build Guide

To build the application, run the following in the `/src` directory:

```bash
javac org/jsoundboard/main/JSoundBoard.java -d ../classes
```

Next, go to the `/classes` directory, make sure a `manifest.txt` file is there, and run the following:

```bash
jar -cvmf ../manifest.txt ../jsoundboard.jar org/
```

*Note:* You don't have to use the path above for the final destination for the jar file. You can put it where ever you feel is good.

To run the application, go to where the jar file is and run:

```bash
java -jar jsoundboard.jar
```

# Design

The application uses the Model-View-Controller (MVC) architecture. The reason for this is to make it clear what code is responsible for. Below is a more detailed explanation of these responsibilities.

## MVC Architecture 

Below is a UML diagram of the Model, View, and Controller classes.

Each has the following responsibilities:

- Model is responsible for handling data and playing sounds.
- View is responsible for display the GUI and allowing the user to play the sounds.
- Controller is responsible for communications between the Model and View, and handling errors when they happen in either the Model or View.

### Model

```mermaid
classDiagram

class Model {
	- File presetFile
	- String filePresetPath
	- Subscriber[] subscribers
	+ subscribe(Subscriber subscriber) void
	+ unsubscribe(Subscriber subscriber) void
	+ notifySubscribers() void
	+ loadPreset(String filePreset) String
	+ savePreset(String filePreset) String
}
class SoundClip {
	- String soundName
	- AudioInputStream audioInputStream
	- Clip clip
	- File soundFile
	+ SoundClip(File soundFile, String soundName)
	+ SoundClip(String filePath, String soundName)
	+ getSoundName() String
	+ getAudioInputStream() AudioInputStream
	+ getSoundClip() Clip
	+ getSoundFile() File
	+ setSoundClipName() void
	+ update(String subscriberName) void
	+ stopSound() void
	+ toString() String
}

Model o-- SoundClip
Model ..> File
SoundClip ..> AudioInputStream
SoundClip ..> Clip
SoundClip ..> File
```

- The Model and SoundClip use the observer design pattern.
	- The rationale for this pattern is to make it easy to play the correct sound when the user clicks the button corresponding to that sound.
- The SoundClip class is responsible for actually loading the sound file and playing it.

### View

```mermaid
classDiagram

class IView {
	+ short DEFAULT_WIDTH
	+ short DEFAULT_WEIGHT
	+ short DEFAULT_SOUND_COUNT
	+ short DEFAULT_BUTTON_GRID_HEIGHT
	+ short DEFAULT_BUTTON_GRID_WIDTH
	+ String WINDOW_TITLE
	+ showMainFrame() void*
	+ addSoundButton(Object buttonListener, String soundName) void*
	+ deleteSoundButton(String soundButtonName) void*
	+ showAboutDialog() void*
	+ showDocumentationDialog() void*
	+ nameSoundButtonDialog() String*
	+ loadPresetFile() String*
	+ savePresetFile() File*
	+ newSoundFilePath() File*
	+ showErrorDialog(String errorMessage) void*
	+ addNewSoundListener(Object newSoundButtonListener) void*
	+ addDeleteSoundListener(Object deleteSoundButtonListener) void*
	+ addAboutDialogListener(Object aboutOptionListener) void*
	+ addDocumentationListener(Object docOptionListener) void*
	+ addSavePresetListener(Object saveOptionListener) void*
	+ addLoadPresetListener(Ojbect loadOptionListener) void*
}
class View {
	- JMenuBar menuBar
	- JMenu menu
	- JMenuItem openPresetOption
	- JMenuItem savePresetOption
	- JMenuItem addSoundOption
	- JMenuItem deleteSoundOption
	- JMenuItem docOption
	- JMenuItem aboutOption
	- JPanel buttonPanel
	- JButton buttons
	- GridLayout buttonLayout
	+ View()
}
class AboutDialog {
	- short DEFAULT_WIDTH
	- short DEFAULT_HEIGHT
	- String applicationTitle
	- String authorAndCopyright
	- String versionNumber
	+ AboutDialog()
}
class DocumentationDialog
class NameSoundDialog {
	- short DEFAULT_WIDTH
	- short DEFAULT_HEIGHT
	- short TEXT_FIELD_COLUMNS
	- JTextField textField
	- String sondName
	- boolean confirmation
	+ NameSoundDialog(JFrame mainFrame)
	+ getTextFieldContent() String
	+ getConfirmation() boolean
}
class okButtonListener {
	+ actionPerformed(ActionEvent event) void
}
class cancelButtonListener {
	+ actionPerformed(ActionEvent event) void
}

IView <|-- View
JFrame <-- View
View *-- NameSoundDialog
JDialog <-- AboutDialog
JDialog <-- DocumentationDialog
JDialog <-- NameSoundDialog
NameSoundDialog *-- okButtonListener
NameSoundDialog *-- cancelButtonListener
ActionListener <| -- okButtonListener
ActionListener <| -- cancelButtonListener
```
