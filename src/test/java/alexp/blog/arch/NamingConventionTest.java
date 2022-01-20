package alexp.blog.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "alexp.blog")
public class NamingConventionTest {
    @ArchTest
    public static final ArchRule controllers_must_have_controller_in_the_name = classes()
            .that().areAnnotatedWith(Controller.class)
            .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    public static final ArchRule services_must_have_service_impl_in_the_name = classes()
            .that().areAnnotatedWith(Service.class)
            .should().haveSimpleNameEndingWith("ServiceImpl");

    @ArchTest
    public static final ArchRule repository_must_be_subclasses_of_JPA_repository = classes()
            .that().areInterfaces().and().areAssignableTo(JpaRepository.class)
            .should().haveSimpleNameEndingWith("Repository");
}
