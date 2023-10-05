package net.runelite.client.plugins.config;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFormattedTextField;
import net.runelite.client.config.Units;

final class UnitFormatter extends JFormattedTextField.AbstractFormatter
{
    private final String units;

    UnitFormatter(Units units)
    {
        this.units = units.value();
    }

    @Override
    public Object stringToValue(final String text) throws ParseException
    {
        final String trimmedText;

        // Using the spinner controls causes the value to have the unit on the end, so remove it
        if (text.endsWith(units))
        {
            trimmedText = text.substring(0, text.length() - units.length());
        }
        else
        {
            trimmedText = text;
        }

        try
        {
            return Integer.valueOf(trimmedText);
        }
        catch (NumberFormatException e)
        {
            throw new ParseException(trimmedText + " is not an integer.", 0); // NOPMD: PreserveStackTrace
        }
    }

    @Override
    public String valueToString(final Object value)
    {
        return value + units;
    }
}

final class UnitFormatterFactory extends JFormattedTextField.AbstractFormatterFactory
{
    UnitFormatterFactory(Units units) {
        this.units = units;
    }
    private final Units units;
    private final Map<JFormattedTextField, JFormattedTextField.AbstractFormatter> formatters = new HashMap<>();

    @Override
    public JFormattedTextField.AbstractFormatter getFormatter(final JFormattedTextField tf)
    {
        return formatters.computeIfAbsent(tf, (key) -> new UnitFormatter(units));
    }
}
