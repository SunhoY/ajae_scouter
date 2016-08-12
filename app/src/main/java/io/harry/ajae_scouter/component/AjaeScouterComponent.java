package io.harry.ajae_scouter.component;

import dagger.Component;
import io.harry.ajae_scouter.activity.AjaeTestActivity;
import io.harry.ajae_scouter.module.GoogleFaceApiModule;

@Component(modules = {
        GoogleFaceApiModule.class,
    }
)
public interface AjaeScouterComponent {
    void inject(AjaeTestActivity ajaeTestActivity);
}
