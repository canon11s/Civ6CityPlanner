package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * ButtonListener class grabbed from the course website in the MVC code.
 * Uses a map of strings to runnables to keep track of all actions that may need to be
 * performed after buttons are clicked, etc.
 */
public class ButtonListener implements ActionListener {
  Map<String,Runnable> buttonClickedActions;

  /**
   * Empty default constructor for the ButtonListener.
   */
  public ButtonListener() {
    /**
     * This constructor is empty because the method setButtonClickedActionMap is used
     * to set the buttonClickedActions field.
     */
  }

  /**
   * Set the map for key typed events. Key typed events in Java Swing are characters
   */
  public void setButtonClickedActionMap(Map<String,Runnable> map) {
    buttonClickedActions = map;
  }

  /**
   * Looks to see if the ActionEvent is in the map of strings to runnables, and if it is,
   * it runs that command with the run() method.
   *
   * @param e ActionEvent that will be looked for and run() called upon if it exists in the map.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {

      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}