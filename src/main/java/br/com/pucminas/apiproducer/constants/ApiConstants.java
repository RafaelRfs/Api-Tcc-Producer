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
    public static final String MSG_ERROR_PROJECT_NOT_FOUND = "projeto nao encontrado";
    public static final String MSG_PROJECT_STARTED = "Projeto criado por %s";
    public static final String MSG_NEW_TIMELINE = "Nova Timeline Criada";
    public static final String MSG_NEW_TIMELINE_CREATED = "Nova timeline %s criada para o projeto: %s";
    public static final String MSG_ERROR_TIMELINE_NOT_FOUND = "Timeline nao encontrada";
    public static final String MSG_ERROR_EMAIL_NOTIFICATICATION = "Email ja cadastrado para o projeto informado";
    public static final String MSG_ERROR_NOTIFICATION_NOT_FOUND = "Notificação nao encontrada";
    public static final String MSG_ERROR = "Usuario já cadastrado no banco de dados";
    public static final String MSG_ERROR_USER_NOT_FOUND = "Usuario não encontrado";
    
    private ApiConstants(){}
}
