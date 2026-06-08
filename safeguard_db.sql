-- ============================================================
--  SafeGuard — Script MySQL completo
--  Projeto Integrador Etapa 4
--  Linguagem: MySQL 8.x
-- ============================================================

CREATE DATABASE IF NOT EXISTS safeguard_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE safeguard_db;

-- ------------------------------------------------------------
-- Tabela: usuarios
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario   INT          NOT NULL AUTO_INCREMENT,
    nome         VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    senha        VARCHAR(64)  NOT NULL,
    perfil       ENUM('ADMIN','AUTORIDADE','OPERADOR') NOT NULL DEFAULT 'OPERADOR',
    ativo        TINYINT(1)   NOT NULL DEFAULT 1,
    criado_em    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_usuario)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- Tabela: vitimas
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS vitimas (
    id_vitima        INT          NOT NULL AUTO_INCREMENT,
    nome             VARCHAR(100) NOT NULL,
    cpf              VARCHAR(14),
    telefone         VARCHAR(20)  NOT NULL,
    endereco         VARCHAR(200) NOT NULL,
    num_medida       VARCHAR(50),
    status_medida    ENUM('ATIVA','SUSPENSA','ENCERRADA') NOT NULL DEFAULT 'ATIVA',
    observacoes      TEXT,
    cadastrado_em    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_vitima)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- Tabela: agressores
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS agressores (
    id_agressor   INT          NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(100) NOT NULL,
    cpf           VARCHAR(14)  NOT NULL,
    telefone      VARCHAR(20),
    endereco      VARCHAR(200),
    descricao     TEXT,
    id_vitima     INT          NOT NULL,
    tipo_vinculo  ENUM('EX_CONJUGE','COMPANHEIRO','FAMILIAR','OUTRO') NOT NULL DEFAULT 'OUTRO',
    cadastrado_em DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_agressor),
    CONSTRAINT fk_agressor_vitima
        FOREIGN KEY (id_vitima) REFERENCES vitimas(id_vitima)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- Tabela: ocorrencias
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ocorrencias (
    id_ocorrencia   INT          NOT NULL AUTO_INCREMENT,
    id_vitima       INT          NOT NULL,
    id_agressor     INT          NOT NULL,
    data_ocorrencia DATE         NOT NULL,
    hora_ocorrencia TIME,
    local_ocorrencia VARCHAR(200) NOT NULL,
    tipo_violencia  ENUM('FISICA','PSICOLOGICA','SEXUAL','PATRIMONIAL','MORAL') NOT NULL,
    descricao       TEXT         NOT NULL,
    status          ENUM('EM_ABERTO','EM_ANALISE','ENCERRADA') NOT NULL DEFAULT 'EM_ABERTO',
    registrado_em   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_ocorrencia),
    CONSTRAINT fk_ocorr_vitima
        FOREIGN KEY (id_vitima) REFERENCES vitimas(id_vitima)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_ocorr_agressor
        FOREIGN KEY (id_agressor) REFERENCES agressores(id_agressor)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- Tabela: alertas
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS alertas (
    id_alerta      INT          NOT NULL AUTO_INCREMENT,
    id_ocorrencia  INT          NOT NULL,
    tipo           ENUM('EMERGENCIA','AVISO','MONITORAMENTO') NOT NULL,
    mensagem       TEXT         NOT NULL,
    status         ENUM('ATIVO','ENCERRADO') NOT NULL DEFAULT 'ATIVO',
    emitido_em     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_alerta),
    CONSTRAINT fk_alerta_ocorr
        FOREIGN KEY (id_ocorrencia) REFERENCES ocorrencias(id_ocorrencia)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ============================================================
-- DADOS INICIAIS PARA TESTE
-- ============================================================

INSERT INTO usuarios (nome, email, senha, perfil) VALUES
('Administrador', 'admin@safeguard.br', '1234', 'ADMIN'),
('Marcelo Santos', 'marcelo@safeguard.br', '1234', 'AUTORIDADE'),
('Wesley Marques', 'wesley@safeguard.br', '1234', 'OPERADOR');

INSERT INTO vitimas (nome, cpf, telefone, endereco, num_medida, status_medida) VALUES
('Ana Paula Silva',   '111.222.333-44', '(27) 99100-2233', 'Rua das Flores, 10, Vitoria - ES', '0012345-67', 'ATIVA'),
('Carla Mendes',      '222.333.444-55', '(27) 98877-5544', 'Av. Maruipe, 200, Vitoria - ES',   '0098765-32', 'ATIVA'),
('Fernanda Rocha',    '333.444.555-66', '(27) 99911-0022', 'Rua Jucutuquara, 55, Vitoria - ES', NULL,        'SUSPENSA');

INSERT INTO agressores (nome, cpf, id_vitima, tipo_vinculo, descricao) VALUES
('Joao da Silva',   '444.555.666-77', 1, 'EX_CONJUGE',  'Historico de violencia fisica.'),
('Roberto Mendes',  '555.666.777-88', 2, 'COMPANHEIRO', 'Descumpriu medida protetiva.');

INSERT INTO ocorrencias (id_vitima, id_agressor, data_ocorrencia, hora_ocorrencia, local_ocorrencia, tipo_violencia, descricao, status) VALUES
(1, 1, '2025-06-04', '14:00', 'Residencia da vitima, Vitoria - ES', 'FISICA',       'Agressor invadiu residencia e ameacou vitima.', 'EM_ABERTO'),
(2, 2, '2025-06-02', '09:00', 'Proximo ao trabalho da vitima',      'PSICOLOGICA',  'Agressor perseguia vitima proxximo ao local de trabalho.', 'EM_ANALISE'),
(3, 1, '2025-05-28', '11:00', 'Via publica, bairro Jardim Camburi', 'MORAL',        'Agressao verbal em via publica.', 'ENCERRADA');

INSERT INTO alertas (id_ocorrencia, tipo, mensagem, status) VALUES
(1, 'EMERGENCIA',    'Agressor avistado proximo a residencia da vitima. Intervencao imediata necessaria.', 'ATIVO'),
(2, 'AVISO',         'Descumprimento de medida protetiva relatado. Autoridades notificadas.', 'ATIVO'),
(3, 'MONITORAMENTO', 'Acompanhamento rotineiro encerrado com sucesso.', 'ENCERRADO');
