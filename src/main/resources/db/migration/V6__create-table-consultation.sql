create table tb_consultation(
    id bigint not null auto_increment,
    id_doctor bigint not null,
    id_patient bigint not null,
    date datetime not null,
    reason varchar(100) null,
    active boolean default true not null,

    primary key (id),
    constraint fk_consultations_id_doctor foreign key (id_doctor) references tb_doctor(id),
    constraint fk_consultations_id_patient foreign key (id_patient) references tb_patient(id)
);