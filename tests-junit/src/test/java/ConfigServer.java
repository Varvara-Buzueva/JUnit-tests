import org.aeonbits.owner.Config;


public interface ConfigServer extends Config {
    @Key("url")
    String url();
}
