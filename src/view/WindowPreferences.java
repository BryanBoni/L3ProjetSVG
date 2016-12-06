/*
 * Set preferences like font or color for the window.
 */
package view;

import java.awt.Color;

/**
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
     * 
     * @param backgroundColor
     * @param textColor
     * @param buttonBackgoundColor
     * @param buttonColor 
     * @param bordBack 
     */
    public WindowPreferences(Color backgroundColor, Color textColor, Color buttonBackgoundColor, Color buttonColor, Color bordBack) {
        this.m_backgroundColor = backgroundColor;
        this.m_textColor = textColor;
        this.m_buttonBackgoundColor = buttonBackgoundColor;
        this.m_buttonColor = buttonColor;
        borderColor = bordBack;
    }

    public static Color getM_backgroundColor() {
        return m_backgroundColor;
    }

    public static void setM_backgroundColor(Color m_backgroundColor) {
        WindowPreferences.m_backgroundColor = m_backgroundColor;
    }

    public static Color getM_textColor() {
        return m_textColor;
    }

    public static void setM_textColor(Color m_textColor) {
        WindowPreferences.m_textColor = m_textColor;
    }

    public static Color getM_buttonBackgoundColor() {
        return m_buttonBackgoundColor;
    }

    public static void setM_buttonBackgoundColor(Color m_buttonBackgoundColor) {
        WindowPreferences.m_buttonBackgoundColor = m_buttonBackgoundColor;
    }

    public static Color getM_buttonColor() {
        return m_buttonColor;
    }

    public static void setM_buttonColor(Color m_buttonColor) {
        WindowPreferences.m_buttonColor = m_buttonColor;
    }

    public static Color getBorderColor() {
        return borderColor;
    }

    public static void setBorderColor(Color borderColor) {
        WindowPreferences.borderColor = borderColor;
    }

   
    
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
     * @return 
     */
    @Override
    public String toString() {
        return "windowPreferences{" + "m_backgroundColor=" + m_backgroundColor + ", m_textColor=" + m_textColor + ", buttonColor=" + m_buttonColor + '}';
    }
    
    
    
}
