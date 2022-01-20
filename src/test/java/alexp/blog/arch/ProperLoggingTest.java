package alexp.blog.arch;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.tngtech.archunit.core.domain.JavaCall.Predicates.target;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.assignableTo;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.name;
import static com.tngtech.archunit.core.domain.properties.HasOwner.Predicates.With.owner;
import static com.tngtech.archunit.core.domain.properties.HasParameterTypes.Predicates.rawParameterTypes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.conditions.ArchConditions.callMethodWhere;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "alexp.blog")
public class ProperLoggingTest {
    @ArchTest
    public static final ArchRule controllers_should_properly_log = methods()
            .that().areAnnotatedWith(RequestMapping.class)
            .should(new ArchCondition<JavaMethod>("Check method call logger") {
                @Override
                public void check(JavaMethod item, ConditionEvents events) {

                    if (item.getMethodCallsFromSelf()
                            .stream()
                            .filter( method -> method.getTargetOwner().isAssignableTo(org.slf4j.Logger.class))
                            .count() == 0) {
                        String message = String.format(
                                "Method %s is not calling any logging", item.getFullName()
                        );
                        events.add(SimpleConditionEvent.violated(item, message));
                    }
                }
            });
}
