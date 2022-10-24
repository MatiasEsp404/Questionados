package com.ntt.questionados.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "RESPONSES")
public class ResponseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "RESPONSE_ID")
  private Long id;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Boolean isRight;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "QUESTION_ID")
  private QuestionEntity question;
}
