/*
 * Set preferences like font or color for the window.
 */
package view;

import java.awt.Color;

/**
 *
 * @author Bryan Boni
 */
public class WindowPreferences {
    
    private Color m_backgroundColor;
    private Color m_textColor;
    private Color m_buttonBackgoundColor;
    private Color m_buttonColor;
    private static Color borderColor;

    /**
     * 
     * @param backgroundColor
     * @param textColor
     * @param buttonBackgoundColor
     * @param buttonColor 
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
     * @return
     */
    public Color getM_backgroundColor() {
        return m_backgroundColor;
    }

    /**
     *
     * @param m_backgroundColor
     */
    public void setM_backgroundColor(Color m_backgroundColor) {
        this.m_backgroundColor = m_backgroundColor;
    }

    /**
     *
     * @return
     */
    public Color getM_textColor() {
        return m_textColor;
    }

    /**
     *
     * @param m_textColor
     */
    public void setM_textColor(Color m_textColor) {
        this.m_textColor = m_textColor;
    }

    /**
     *
     * @return
     */
    public Color getM_buttonBackgoundColor() {
        return m_buttonBackgoundColor;
    }

    /**
     *
     * @param m_buttonBackgoundColor
     */
    public void setM_buttonBackgoundColor(Color m_buttonBackgoundColor) {
        this.m_buttonBackgoundColor = m_buttonBackgoundColor;
    }

    /**
     *
     * @return
     */
    public Color getM_buttonColor() {
        return m_buttonColor;
    }

    /**
     *
     * @param m_buttonColor
     */
    public void setM_buttonColor(Color m_buttonColor) {
        this.m_buttonColor = m_buttonColor;
    }

    public static Color getBorderColor() {
        return borderColor;
    }

    public static void setBorderColor(Color borderColor) {
        WindowPreferences.borderColor = borderColor;
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
