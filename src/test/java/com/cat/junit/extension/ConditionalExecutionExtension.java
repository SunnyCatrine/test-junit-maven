package com.cat.junit.extension;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ConditionalExecutionExtension implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
        ConditionEvaluationResult result;
        if (extensionContext.getConfigurationParameter("skip_parameter").isPresent()) {
            result = ConditionEvaluationResult.disabled("skip");
        } else {
            result = ConditionEvaluationResult.enabled("don't skip");
        }
        return result;
    }
}
