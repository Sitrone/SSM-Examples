package io.sununiq.conf;

public @interface AppConf {
    String name() default "Zhang San";

    int age() default 18;

    String address() default "";
}
