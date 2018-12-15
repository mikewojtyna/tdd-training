package pro.buildmysoftware.training.tdd.kata;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.time.LocalTime;

public class TimeConverter implements ArgumentConverter {
	@Override
	public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
		return LocalTime.parse((CharSequence) source);
	}
}
