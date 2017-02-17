package org.ballerinalang.nativeimpl.lang.string;

import org.ballerinalang.bre.Context;
import org.ballerinalang.model.types.TypeEnum;
import org.ballerinalang.model.values.BInteger;
import org.ballerinalang.model.values.BString;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.natives.AbstractNativeFunction;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;
import org.ballerinalang.util.exceptions.BallerinaException;

/**
 * Native function ballerina.model.array:subString(string, int, int).
 */
@BallerinaFunction(
        packageName = "ballerina.lang.string",
        functionName = "subString",
        args = {@Argument(name = "mainString", type = TypeEnum.STRING),
                @Argument(name = "from", type = TypeEnum.INT),
                @Argument(name = "to", type = TypeEnum.INT)},
        returnType = {@ReturnType(type = TypeEnum.STRING)},
        isPublic = true
)
public class SubString extends AbstractNativeFunction {

    @Override
    public BValue[] execute(Context context) {
        String initialString = getArgument(context, 0).stringValue();
        BInteger argFrom = (BInteger) getArgument(context, 1);
        BInteger argTo = (BInteger) getArgument(context, 2);

        int from = argFrom.intValue();
        int to = argTo.intValue();
        if (from < 0 || to > initialString.length()) {
            throw new BallerinaException("String index out of range. Actual:" + initialString.length() +
                    " requested: " + from + " to " + to);
        }
        BString subString = new BString(initialString.substring(from, to));
        return getBValues(subString);
    }
}