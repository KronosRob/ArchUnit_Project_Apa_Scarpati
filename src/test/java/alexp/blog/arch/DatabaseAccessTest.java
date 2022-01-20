package alexp.blog.arch;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "alexp.blog")
public class DatabaseAccessTest {
    @ArchTest
    public static final ArchRule no_direct_access_to_db = noClasses()
            .should().accessClassesThat(JavaClass.Predicates.assignableTo(EntityManager.class));
}
