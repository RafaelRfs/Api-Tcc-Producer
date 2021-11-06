package br.com.pucminas.apiproducer.constants;

public class ApiConstants {

    public static final String RABBIT_QUEUE_NAME = "${spring.rabbitmq.queue}";
    public static final String EMAIL_REGEX = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+";
    public static final String TASK_EXECUTOR = "task_executor_tcc";
    public static final String THREAD_POOL_TASK = "threadPoolTaskExecutor";
    public static final String MSG_SUBJECT_NEW_PROJECT = "Novo Projeto %s Criado: {}";
    public static final String MSG_BODY_PROJECT = " Projeto: %s \\n Cliente: %s";
    public static final String MSG_USER_NOT_AUTHORIZED = " Ação não permitida para o seu usuário ";
    public static final String MSG_ERROR_EMAIL_EXISTS = "Novo email ja existente na base de dados";
    public static final String MSG_PASSWORD_INVALID = " senha antiga invalida ";
    public static final String MSG_CONFIRMATION_NEW_PASS_INVALID = " senha de confirmacao diferente da nova senha";

    public static final String MSG_NEW_TIMELINE = "Nova Timeline Criada";
    public static final String MSG_NEW_TIMELINE_CREATED = "Nova timeline %s criada para o projeto: %s";
    private ApiConstants(){}
}
