create table tb_consultation(
    id bigint not null auto_increment,
    doctor_id bigint not null,
    patient_id bigint not null,
    date datetime not null,
    reason varchar(100) null,
    active boolean default true not null,

    primary key (id),
    constraint fk_consultations_doctor_id foreign key (doctor_id) references tb_doctor(id),
    constraint fk_consultations_patient_id foreign key (patient_id) references tb_patient(id)
);