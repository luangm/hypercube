import io.luan.exp4j.expression.Expression;
import io.luan.hypercube.entity.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Miao on 5/20/2016.
 */
public class MainTest {

    @Test
    public void test1() {
        Concept assetConcept = new Concept("Asset");
        Concept liabConcept = new Concept("Liability");
        Concept equityConcept = new Concept("Equity");

        Context context = new Context();

        Period period = new Period(LocalDate.of(2016, 1,1));

        Fact asset = new Fact();
        asset.setConcept(assetConcept);
        asset.setContext(context);
        asset.setPeriod(period);
        asset.setQuantity(new Quantity(100, "USD"));

        Fact liability  = new Fact();
        asset.setConcept(liabConcept);
        asset.setContext(context);
        asset.setPeriod(period);
        asset.setQuantity(new Quantity(200, "USD"));

        ComputedFact equity = new ComputedFact();
        equity.setConcept(equityConcept);
        equity.setContext(context);
        equity.setPeriod(period);
        equity.setFormula("Asset + Liability");

        Hypercube cube = new Hypercube();
        cube.addFact(asset);
        cube.addFact(liability);
        cube.addFact(equity);


        Expression exp = Expression.parse(equity.getFormula());
        System.out.println(exp.getVariables());
    }
}
