//
//  JokesViewModelWrapper.swift
//  JokesApp
//
//  Created by Kurt Renzo Acosta on 7/10/21.
//  Copyright © 2021 Kurt. All rights reserved.
//

import SwiftUI
import JokesShared

class JokesViewModelWrapper : ViewModelWrapper, ObservableObject {
  @Published var jokes: [Joke] = []
  @Published var jokesState: UiState = UiState.Loading()
  
  let viewModel = JokesViewModel.Companion.init().create()
  
  func onStart() {
    viewModel.jokes.watch { (jokesNullable) in
      guard let ktJokes = jokesNullable as? [Joke] else {
        return
      }
      self.jokes = ktJokes
    }.addToCloseables(self)
    
    viewModel.jokesState.watch { (uiStateNullable) in
      guard let uiState = uiStateNullable else {
        return
      }
      self.jokesState = uiState
    }.addToCloseables(self)
  }
}
