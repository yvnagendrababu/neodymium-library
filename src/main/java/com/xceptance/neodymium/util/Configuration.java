package com.xceptance.neodymium.util;

import static org.aeonbits.owner.Config.DisableableFeature.VARIABLE_EXPANSION;

import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

@LoadPolicy(LoadType.MERGE)
@Sources(
{
  "file:config/test.properties", "file:config/credentials.properties"
})
public interface Configuration extends Mutable
{
    // standard timeout for selenide interaction
    @Key("selenide.timeout")
    @DefaultValue("3000")
    public long timeout();

    @Key("stale.retry.count")
    @DefaultValue("3")
    public int staleElementRetryCount();

    @Key("stale.retry.timeout")
    @DefaultValue("500")
    public int staleElementRetryTimeout();

    @Key("javascript.timeout")
    @DefaultValue("2000")
    public int javaScriptTimeout();

    @Key("javascript.pool.interval")
    @DefaultValue("200")
    public int javaScriptPoolInterval();

    @Key("javascript.pool.mustHaveBeenActive")
    @DefaultValue("true")
    public boolean javaScriptMustHaveBeenActive();

    @Key("url")
    public String url();

    @Key("url.protocol")
    public String protocol();

    @Key("url.host")
    public String host();

    @Key("url.path")
    public String path();

    @Key("url.path")
    @DisableFeature(
    {
      VARIABLE_EXPANSION
    })
    public String rawPath();

    @Key("url.site")
    public String site();

    @Key("locale")
    @DefaultValue("default")
    public String locale();

    @Key("localization.file")
    @DefaultValue("config/localization.yaml")
    public String localizationFile();

    @Key("email.domain")
    @DefaultValue("varmail.de")
    public String emailDomain();

    @Key("email.local.prefix")
    @DefaultValue("test")
    public String emailLocalPrefix();

    @Key("screenshots.perstep.always")
    @DefaultValue("false")
    public boolean screenshotPerStep();

    @Key("device.breakpoint.small")
    @DefaultValue("544")
    public int smallDeviceBreakpoint();

    @Key("device.breakpoint.medium")
    @DefaultValue("769")
    public int mediumDeviceBreakpoint();

    @Key("device.breakpoint.large")
    @DefaultValue("992")
    public int largeDeviceBreakpoint();

    @Key("device.breakpoint.xlarge")
    @DefaultValue("1200")
    public int xlargeDeviceBreakpoint();
}