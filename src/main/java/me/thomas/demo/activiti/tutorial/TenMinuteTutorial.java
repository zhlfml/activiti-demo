package me.thomas.demo.activiti.tutorial;

import org.activiti.engine.*;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TenMinuteTutorial {

    public static void main(String[] args) {

        // Create Activiti process engine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // Get Activiti services
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();

        // Deploy process
        if (repositoryService.createProcessDefinitionQuery().processDefinitionKey("askForLeave").count() == 0) {
            repositoryService.createDeployment().addClasspathResource("askForLeave.bpmn").deploy().getId();
        }

        String processInstanceId = runtimeService.startProcessInstanceByKey("askForLeave").getId();

        // Do the first task
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("manager").active().list();
        System.out.println("manager's tasks count => " + tasks.size());
        for (Task task : tasks) {
            // claim it
            taskService.claim(task.getId(), "thomas");
        }
        // Verify that thomas can retrieve the task
        tasks = taskService.createTaskQuery().taskAssignee("thomas").active().list();
        System.out.println("thomas' tasks count => " + tasks.size());
        for (Task task : tasks) {
            System.out.println("task.getId() => " + task.getId());
            // complete it
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("approved", true);

            taskService.complete(task.getId(), variables);
        }

        tasks = taskService.createTaskQuery().taskAssignee("thomas").active().list();
        System.out.println("after complete tasks, thomas' tasks count = > " + tasks.size());

        // 找出当前节点
        Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(execution.getId());
        for (String activeActivityId : activeActivityIds) {
            System.out.println("activeActivityId => " + activeActivityId);
        }

        // Do the second task
        tasks = taskService.createTaskQuery().taskCandidateGroup("hr").active().list();
        for (Task task : tasks) {
            System.out.println("task.getId() => " + task.getId());
            // claim it
            taskService.claim(task.getId(), "celine");
        }
        // Verify that celine can retrieve the task
        tasks = taskService.createTaskQuery().taskAssignee("celine").active().list();
        for (Task task : tasks) {
            // complete it
            taskService.complete(task.getId());
        }

    }

}
