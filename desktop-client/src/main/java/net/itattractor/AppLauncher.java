package net.itattractor;

import jxgrabkey.HotkeyListener;
import net.itattractor.config.Config;
import net.itattractor.controller.LoginFormController;
import net.itattractor.controller.RecordFormController;
import net.itattractor.controller.TasksFormController;
import net.itattractor.controller.TrayController;
import net.itattractor.forms.login.LoginForm;
import net.itattractor.forms.record.RecordForm;
import net.itattractor.forms.tasks.TasksForm;
import net.itattractor.forms.tray.Tray;
import net.itattractor.manager.WindowManager;
import net.itattractor.screenshot.TimerTaskImpl;
import net.itattractor.states.LoginFormState;
import net.itattractor.states.RecordFormState;
import net.itattractor.states.TasksFormState;
import javax.swing.*;
import jxgrabkey.HotkeyListener;

public class AppLauncher {
    private WindowManager manager;
    private TasksFormController tasksFormController;
    private Config config;
    private TimeProvider timeProvider;
    private TrayController trayController;

    public void init(){

        HotkeyListener hotkeyListener = new HotkeyListener() {
            @Override
            public void onHotkey(int i) {
                manager.show();
            }
        };
        HotKeyRegister hotKeyRegister = new HotKeyRegister();
        hotKeyRegister.register(hotkeyListener);

        manager = new WindowManager();
        timeProvider = createTimeProvider();

        LoginForm  loginForm = new LoginForm();
        LoginFormState loginFormState = new LoginFormState();
        loginFormState.setForm(loginForm);
        manager.setLoginFormState(loginFormState);

        TasksFormState tasksFormState = new TasksFormState();
        TasksForm tasksForm = new TasksForm();
        tasksFormState.setForm(tasksForm);
        tasksFormState.setHotKeyRegister(hotKeyRegister);
        manager.setTasksFormState(tasksFormState);

        RecordFormState recordFormState = new RecordFormState();
        RecordForm recordForm = new RecordForm();
        recordFormState.setForm(recordForm);
        manager.setRecordFormState(recordFormState);
        recordFormState.setManager(manager);

        manager.init();

        Tray tray = new Tray();
        trayController = new TrayController(manager, tray);
        trayController.setHotKeyRegister(hotKeyRegister);

        LoginFormController loginFormController = new LoginFormController(loginForm, manager);
        tasksFormController = new TasksFormController(manager);
        EventCounter eventCounter = new EventCounter();
        tasksFormController.setEventCounter(eventCounter);
        tasksFormController.setConfig(config);
        tasksForm.setActionListener(tasksFormController);
        tasksFormController.setTimeProvider(timeProvider);

        RecordFormController recordFormController = new RecordFormController(recordForm, manager);
        recordFormController.setConfig(config);
        WorkLogSender workLogSender = new WorkLogSender();
        workLogSender.setTimeProvider(timeProvider);
        workLogSender.setConfig(config);
        recordFormController.setWorkLogSender(workLogSender);
        loginFormController.start();

    }

    private TimeProvider createTimeProvider() {
        return Boolean.parseBoolean(config.getValue("testMode")) ?  new FakeTimeProvider() : new SystemTimeProvider();
    }

    public JFrame getMainFrame() {
        return manager.getFrame();
    }

    public TimerTaskImpl getTimerTask() {
        return tasksFormController.getTimerTask();
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public TimeProvider getTimeProvider() {
        return timeProvider;
    }
}
