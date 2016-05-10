package pl.finder.elmer.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode
@ToString
public final class ExchangeDefinition {
    private final String name;
    private final ExchangeType type;
    private final boolean passive;
    private final boolean durable;
    private final boolean autoDeletable;
    private final boolean internal;
    private final boolean delayed;

    public static ExchangeDefinition createDefault(final String name) {
        return configurator()
                .name(name)
                .type(ExchangeType.Fanout)
                .create();
    }

    public static ExchangeDefinition.Configurator configurator() {
        return new Configurator();
    }

    public ExchangeDefinition.Configurator reconfigure() {
        return configurator()
                .name(name)
                .type(type)
                .passive(passive)
                .durable(durable)
                .autoDeletable(autoDeletable)
                .internal(internal)
                .deleyed(delayed);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Setter
    @Accessors(fluent = true)
    public static final class Configurator {
         private String name;
         private ExchangeType type = ExchangeType.Fanout;
         private boolean passive;
         private boolean durable = true;
         private boolean autoDeletable;
         private boolean internal;
         private boolean deleyed;

        public ExchangeDefinition create() {
            checkArgument(!isNullOrEmpty(name), "Exchange name not specified");
            checkArgument(type != null, "Exchange type not specified");
            return new ExchangeDefinition(name, type,
                    passive, durable, autoDeletable, internal, deleyed);
        }
    }
}
