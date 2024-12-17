package libraryservice.infra;

import libraryservice.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class LoanHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Loan>> {

    @Override
    public EntityModel<Loan> process(EntityModel<Loan> model) {
        return model;
    }
}
