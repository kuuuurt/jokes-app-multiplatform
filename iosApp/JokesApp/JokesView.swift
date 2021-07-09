//
//  JokesView.swift
//  JokesApp
//
//  Created by Kurt Renzo Acosta on 7/10/21.
//  Copyright © 2021 Kurt. All rights reserved.
//

import SwiftUI
import JokesShared

struct JokesView: View {
  @StateObject var viewModel = JokesViewModelWrapper()
  
  var body: some View {
    ZStack {
      if (viewModel.jokesState is UiState.Loading) {
        ProgressView()
      } else {
        List {
          ForEach(viewModel.jokes, id: \.id) { joke in
            VStack(alignment: .leading) {
              Text(joke.setup).fontWeight(.bold)
              Text(joke.punchline)
            }.padding(8)
          }
        }
      }
    }.onAppear {
      viewModel.onStart()
    }
  }
}

struct JokesView_Previews: PreviewProvider {
  static var previews: some View {
    JokesView()
  }
}
