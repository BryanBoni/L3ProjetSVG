package view;

import java.awt.Color;

/**
 * Set preferences for the theme apply to the window.
 * 
 * @author BryanBoni
 */
public class WindowPreferences {
    
    private static Color m_backgroundColor;
    private static Color m_textColor;
    private static Color m_buttonBackgoundColor;
    private static Color m_buttonColor;
    private static Color borderColor;

    /**
     * Constructor for the WindowPreferences, initiate the theme preference.
     * 
     * @param backgroundColor general background color.
     * @param textColor general text color.
     * @param buttonBackgoundColor general background color for all buttons.
     * @param buttonColor general text color for all buttons.
     * @param bordBack specific color for border, optional.
     */
    public WindowPreferences(Color backgroundColor, Color textColor, Color buttonBackgoundColor, Color buttonColor, Color bordBack) {
        this.m_backgroundColor = backgroundColor;
        this.m_textColor = textColor;
        this.m_buttonBackgoundColor = buttonBackgoundColor;
        this.m_buttonColor = buttonColor;
        borderColor = bordBack;
    }

    /**
     * 
     * @return the background color value.
     */
    public static Color getM_backgroundColor() {
        return m_backgroundColor;
    }

    /**
     * Define a new background color.
     * 
     * @param m_backgroundColor the new background color value.
     */
    public static void setM_backgroundColor(Color m_backgroundColor) {
        WindowPreferences.m_backgroundColor = m_backgroundColor;
    }

    /**
     * 
     * @return the text color value. 
     */
    public static Color getM_textColor() {
        return m_textColor;
    }

    /**
     * Define a new text color.
     * 
     * @param m_textColor the new text color value.
     */
    public static void setM_textColor(Color m_textColor) {
        WindowPreferences.m_textColor = m_textColor;
    }

    /**
     * 
     * @return the button background color value. 
     */
    public static Color getM_buttonBackgoundColor() {
        return m_buttonBackgoundColor;
    }

    /**
     * Define a new color for the background color of all button.
     * 
     * @param m_buttonBackgoundColor the new button background color value.
     */
    public static void setM_buttonBackgoundColor(Color m_buttonBackgoundColor) {
        WindowPreferences.m_buttonBackgoundColor = m_buttonBackgoundColor;
    }

    /**
     * 
     * @return the text button color value.
     */
    public static Color getM_buttonColor() {
        return m_buttonColor;
    }

    /**
     * Define a new color for the text in a button.
     * 
     * @param m_buttonColor the new color value for the text in a button.
     */
    public static void setM_buttonColor(Color m_buttonColor) {
        WindowPreferences.m_buttonColor = m_buttonColor;
    }

    /**
     * 
     * @return specific border color value.
     */
    public static Color getBorderColor() {
        return borderColor;
    }

    /**
     * Define a new color for specific borders.
     * 
     * @param borderColor the new color value for specific borders.
     */
    public static void setBorderColor(Color borderColor) {
        WindowPreferences.borderColor = borderColor;
    }

   
    /**
     * Use to change the theme of the program, 
     * which mean changing the background, text, background button, 
     * button and specific border Color.
     * 
     * @param backgroundColor general background color.
     * @param textColor general text color.
     * @param buttonBackgoundColor general background color for all buttons.
     * @param buttonColor general text color for all buttons.
     * @param bordBack specific color for border, optional. 
     */
    public static void changePref(Color backgroundColor, Color textColor, Color buttonBackgoundColor, Color buttonColor, Color bordBack){
        WindowPreferences.m_backgroundColor = backgroundColor;
        WindowPreferences.m_textColor = textColor;
        WindowPreferences.m_buttonBackgoundColor = buttonBackgoundColor;
        WindowPreferences.m_buttonColor = buttonColor;
        WindowPreferences.borderColor = bordBack;
    }
    
    /**
     * Show current preferences.
     * 
     * @return a string which contain all the values of the current theme.
     */
    @Override
    public String toString() {
        return "windowPreferences{" + "m_backgroundColor=" + m_backgroundColor + ", m_textColor=" + m_textColor + ", buttonColor=" + m_buttonColor + '}';
    }
}
