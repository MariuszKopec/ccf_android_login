package com.ccf.main.application;

import android.app.Application;

import com.ccf.android.repository.login.memory.LoginRepositoryMemory;
import com.ccf.android.ui.di.Injector;
import com.ccf.android.ui.executor.JobExecutor;
import com.ccf.android.ui.executor.UIThread;
import com.ccf.logic.executor.PostExecutionThread;
import com.ccf.logic.executor.ThreadExecutor;
import com.ccf.logic.repository.Repository;
import com.ccf.main.login.LoginModule;

import dagger.ObjectGraph;

public class CcfApplication extends Application implements Injector {
    private ObjectGraph graph;
    private JobExecutor jobExecutor;
    private UIThread uiThread;
    private Repository repository;

    private static CcfApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(new LoginModule());
        jobExecutor = new JobExecutor();
        uiThread = new UIThread();
        repository = new LoginRepositoryMemory();
        instance = this;
    }

    public static CcfApplication getInstance() {
        return instance;
    }

    public static void setApplication(CcfApplication application) {
        CcfApplication.instance = application;
    }

    @Override
    public void inject(Object object) {
        graph.inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return graph;
    }

    public ThreadExecutor getThreadExecutor() {
        return jobExecutor;
    }

    public PostExecutionThread getPostExecutionThread() {
        return uiThread;
    }

    public Repository getRepository() {
        return repository;
    }
}